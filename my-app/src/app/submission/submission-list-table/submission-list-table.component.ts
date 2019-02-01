import {Component, EventEmitter, Input, OnInit, Output} from '@angular/core';
import {Page} from '../../shared/model/paging/page.model';
import {BriefSubmission} from '../../shared/model/submission/brief-submission.model';
import {TokenService} from '../../core/service/token.service';
import {Submission} from '../../shared/model/submission/submission.model';
import {BriefUserRoles} from '../../shared/model/user/brief-user-roles.model';
import {ConferenceRoleService} from '../../core/service/utils/conference-role.service';

@Component({
  selector: 'app-submission-list-table',
  templateUrl: './submission-list-table.component.html',
  styleUrls: ['./submission-list-table.component.css']
})
export class SubmissionListTableComponent implements OnInit {

  @Input()
  page: Page<BriefSubmission>;

  @Output()
  onPageChange = new EventEmitter<any>();

  @Input()
  userRoles: BriefUserRoles;

  constructor(public tokenService: TokenService, private conferenceRoleService: ConferenceRoleService) {

  }

  ngOnInit() {

  }

  pageChange(page: number) {
    this.onPageChange.emit(page);
  }

  // вызывается триллиард раз, может переделать как-нибудь
  isClickable(submission: Submission) {
    return this.isReviewer(submission) || this.isAuthor(submission) || this.isAdmin();
  }

  private isReviewer(submission: Submission) {
    return submission.reviewers.find(value => value.username === this.tokenService.getUsername()) !== undefined;
  }

  private isAuthor(submission: Submission) {
    return submission.author.username === this.tokenService.getUsername();
  }

  private isAdmin() {
    if (this.userRoles !== undefined) {
      const set = new Set(this.userRoles.roles);
      return set.has(this.conferenceRoleService.getAdminNumber()) || set.has(this.conferenceRoleService.getCreatorNumber());
    }
  }


}
