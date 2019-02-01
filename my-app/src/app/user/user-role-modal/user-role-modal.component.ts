import {Component, EventEmitter, Input, OnChanges, OnInit, Output} from '@angular/core';
import {ModalService} from '../../core/service/modal.service';
import {FormBuilder, FormGroup, Validators} from '@angular/forms';
import {ConferenceRoleService} from '../../core/service/utils/conference-role.service';
import {BriefUserRoles} from '../../shared/model/user/brief-user-roles.model';
import {ConferenceService} from '../../core/service/conference.service';

@Component({
  selector: 'app-user-role-modal',
  templateUrl: './user-role-modal.component.html',
  styleUrls: ['./user-role-modal.component.css']
})
export class UserRoleModalComponent implements OnInit {

  form: FormGroup;

  selectedRoles: Set<number>;

  // inputs
  conferenceId: number;
  user: BriefUserRoles;
  updatedUser: BriefUserRoles;

  @Output()
  onModalSubmitted = new EventEmitter<BriefUserRoles>();

  constructor(private modalService: ModalService,
              private fb: FormBuilder,
              private conferenceService: ConferenceService,
              public conferenceRoleService: ConferenceRoleService) {
    this.createForm();
  }

  ngOnInit() {
    this.selectedRoles = new Set<number>(this.user.roles);

  }


  createForm() {
    this.form = this.fb.group({
      role: ['qwe', Validators.required],
    });
  }

  close() {
    this.modalService.destroy();
  }

  submit() {
    this.conferenceService.addRolesToUser(this.conferenceId, this.user.id, this.selectedRoles)
      .subscribe((data) => {
        this.updatedUser = data;
        this.onModalSubmitted.emit(data);
        this.close();
      });
  }

  onRoleChange(event: any) {
    if (this.selectedRoles.has(Number(event.target.getAttribute('value')))) {
      this.selectedRoles.delete(Number(event.target.getAttribute('value')));
    } else {
      this.selectedRoles.add(Number(event.target.getAttribute('value')));
    }
  }
}
