import {Component, OnInit} from '@angular/core';
import {Page} from '../../shared/model/paging/page.model';
import {BriefUser} from '../../shared/model/user/brief-user.model';
import {SubmissionService} from '../../core/service/submission.service';
import {ActivatedRoute} from '@angular/router';
import {FormBuilder, FormGroup, Validators} from '@angular/forms';
import 'rxjs/add/operator/debounceTime';
import {ConferenceService} from '../../core/service/conference.service';
import {BriefUserRoles} from '../../shared/model/user/brief-user-roles.model';
import {Submission} from '../../shared/model/submission/submission.model';
import {ConferenceRoleService} from '../../core/service/utils/conference-role.service';
import {Location} from '@angular/common';

@Component({
  selector: 'app-reviewer-list',
  templateUrl: './reviewer-list.component.html',
  styleUrls: ['./reviewer-list.component.css']
})
export class ReviewerListComponent implements OnInit {

  page: Page<BriefUser>;
  submissionId: number;
  submission: Submission;
  form: FormGroup;
  searchedUsers: Page<BriefUserRoles>;
  newReviewers: Page<BriefUserRoles>;
  userRoles: BriefUserRoles;

  constructor(private fb: FormBuilder,
              private route: ActivatedRoute,
              private submissionService: SubmissionService,
              private conferenceService: ConferenceService,
              private conferenceRoleService: ConferenceRoleService,
              private location: Location) {
    this.createForm();
  }

  ngOnInit() {
    this.route.params.subscribe(params => {
        if (params['submissionId']) {
          this.submissionId = params['submissionId'];
          this.submissionService.getSubmission(this.submissionId).subscribe(data => {
            this.submission = data;
            this.conferenceService.getReviewers(this.submission.conferenceId).subscribe(data => this.newReviewers = data);
            this.fetchUserRoles();
          });
          this.updateReviewersData();
        }
      }
    );
    this.form.controls['search'].valueChanges.debounceTime(500).subscribe(() => this.updateSearchData());
  }

  pageChange(page: number) {
    this.page.number = page;
    this.updateReviewersData();
  }

  updateReviewersData() {
    this.submissionService.getReviewers(this.submissionId, this.page ? this.page.number : 0).subscribe((data => this.page = data));
  }

  createForm() {
    this.form = this.fb.group({
      search: ['', Validators.required],
    });
  }

  updateSearchData() {
    this.conferenceService
      .getUsers(this.submission.conferenceId,
        this.searchedUsers ? this.searchedUsers.number : 0,
        this.conferenceRoleService.getReviewerNumber(),
        this.form.value.search)
      .subscribe(data => {
        console.log(data);
        this.searchedUsers = data;
      });
  }

  addReviewers(reviewerId: number) {
    this.submissionService.addReviewers(this.submissionId, [reviewerId]).subscribe(() => this.updateReviewersData());
  }

  deleteReviewers(u: BriefUser) {
    this.submissionService.deleteReviewers(this.submissionId, [u.id])
      .subscribe(() => this.updateReviewersData());
  }

  goBack(): void {
    this.location.back();
  }

  isAdmin() {
    if (this.userRoles !== undefined) {
      const set = new Set(this.userRoles.roles);
      return set.has(this.conferenceRoleService.getCreatorNumber()) || set.has(this.conferenceRoleService.getAdminNumber());
    }
  }

  private fetchUserRoles() {
    this.conferenceService.getUserRoles(this.submission.conferenceId).subscribe(data => this.userRoles = data);
  }

}
