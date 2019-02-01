import {Component, EventEmitter, Input, OnInit, Output} from '@angular/core';
import {Page} from '../../shared/model/paging/page.model';
import {BriefUserRoles} from '../../shared/model/user/brief-user-roles.model';
import {ConferenceRoleService} from '../../core/service/utils/conference-role.service';
import {SigninModalComponent} from '../../signin/signin-modal/signin-modal.component';
import {ModalService} from '../../core/service/modal.service';
import {UserRoleModalComponent} from '../user-role-modal/user-role-modal.component';
import {Observable} from 'rxjs/Observable';
import {ConferenceService} from '../../core/service/conference.service';

@Component({
  selector: 'app-conference-users-table',
  templateUrl: './conference-users-table.component.html',
  styleUrls: ['./conference-users-table.component.css']
})
export class ConferenceUsersTableComponent implements OnInit {

  @Input()
  page: Page<BriefUserRoles>;

  @Input()
  conferenceId: number;

  @Output()
  onPageChange = new EventEmitter<any>();

  userRoles: BriefUserRoles;

  constructor(public conferenceRoleService: ConferenceRoleService,
              private modalService: ModalService,
              private conferenceService: ConferenceService) {
  }

  ngOnInit() {
    this.conferenceService.getUserRoles(this.conferenceId).subscribe(data => this.userRoles = data);
  }

  pageChange(page: number) {
    this.onPageChange.emit(page);
  }

  initRoleModal(user: BriefUserRoles) {
    if (this.modalService.isLoaded()) {
      this.modalService.destroy();
    }
    const inputs = {
      user: user,
      conferenceId: this.conferenceId
    };

    const outputs = {onModalSubmitted: new EventEmitter<BriefUserRoles>()};
    this.modalService.init(UserRoleModalComponent, inputs, outputs);
    outputs.onModalSubmitted.subscribe((data) => {
      const index = this.page.content.indexOf(this.page.content.find(value => value.id === data.id));
      this.page.content[index] = data;
    });
  }

  isAdmin() {
    if (this.userRoles !== undefined) {
      const set = new Set(this.userRoles.roles);
      return set.has(this.conferenceRoleService.getCreatorNumber()) || set.has(this.conferenceRoleService.getAdminNumber());
    }
  }

}
