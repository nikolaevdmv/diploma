import {Component, EventEmitter, Input, OnInit, Output} from '@angular/core';
import {Page} from '../../shared/model/paging/page.model';
import {BriefUser} from '../../shared/model/user/brief-user.model';
import {SubmissionService} from '../../core/service/submission.service';
import {ConferenceService} from '../../core/service/conference.service';
import {BriefUserRoles} from '../../shared/model/user/brief-user-roles.model';
import {ConferenceRoleService} from '../../core/service/utils/conference-role.service';

@Component({
  selector: 'app-reviewer-list-table',
  templateUrl: './reviewer-list-table.component.html',
  styleUrls: ['./reviewer-list-table.component.css']
})
export class ReviewerListTableComponent implements OnInit {

  @Input()
  page: Page<BriefUser>;

  @Input()
  conferenceId: number;

  @Output()
  onPageChange = new EventEmitter<any>();

  @Output()
  onDeleteReviewer = new EventEmitter<BriefUser>();

  userRoles: BriefUserRoles;


  constructor(private submissionService: SubmissionService,
              private conferenceService: ConferenceService,
              private conferenceRoleService: ConferenceRoleService) {

  }

  ngOnInit() {
    this.conferenceService.getUserRoles(this.conferenceId).subscribe(data => this.userRoles = data);
  }

  pageChange(page: number) {
    this.onPageChange.emit(page);

  }

  deleteReviewer(u: BriefUser) {
    this.onDeleteReviewer.emit(u);
  }

  isAdmin() {
    if (this.userRoles !== undefined) {
      const set = new Set(this.userRoles.roles);
      return set.has(this.conferenceRoleService.getCreatorNumber()) || set.has(this.conferenceRoleService.getAdminNumber());
    }
  }
}
