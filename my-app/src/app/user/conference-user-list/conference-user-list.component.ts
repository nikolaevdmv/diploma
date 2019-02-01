import {Component, OnInit, ViewChild} from '@angular/core';
import {ConferenceService} from '../../core/service/conference.service';
import {ActivatedRoute} from '@angular/router';
import {BriefUserRoles} from '../../shared/model/user/brief-user-roles.model';
import {Page} from '../../shared/model/paging/page.model';
import {FormBuilder, FormGroup, Validators} from '@angular/forms';
import {ConferenceRoleService} from '../../core/service/utils/conference-role.service';
import {UserService} from '../../core/service/user.service';
import {BriefUser} from '../../shared/model/user/brief-user.model';

@Component({
  selector: 'app-conference-user-list',
  templateUrl: './conference-user-list.component.html',
  styleUrls: ['./conference-user-list.component.css']
})
export class ConferenceUserListComponent implements OnInit {

  page: Page<BriefUserRoles>;
  form: FormGroup;
  searchForm: FormGroup;
  selectedRole: number;

  conferenceId: number;
  userRole = ['Все', 'Организатор', 'Докладчик', 'Рецензент', 'Админ'];

  userRoles: BriefUserRoles;
  searchedUsers: Page<BriefUser>;

  constructor(private fb: FormBuilder,
              private route: ActivatedRoute,
              private conferenceService: ConferenceService,
              private userService: UserService,
              private conferenceRoleService: ConferenceRoleService) {
    this.createForm();
    this.createSearchForm();
  }

  ngOnInit() {
    this.route.params.map(params => {
      if (params['conferenceId']) {
        this.conferenceId = params['conferenceId'];
        this.fetchUserRoles();
      }
    }).mergeMap(() =>
      this.conferenceService.getUsers(this.conferenceId))
      .subscribe(data => {
        this.page = data;
      });
    this.searchForm.controls['search'].valueChanges.debounceTime(500).subscribe(() => this.updateSearchData());
  }

  submit(value: any) {
    this.selectedRole = this.conferenceRoleService.getRoleNumber(value.role);
    this.updateUsersData();
  }


  pageChange(page: number) {
    this.page.number = page;
    this.updateUsersData();
  }

  updateUsersData() {
    this.conferenceService
      .getUsers(this.conferenceId, this.page ? this.page.number : 0, this.selectedRole)
      .subscribe((data => this.page = data));
  }

  createForm() {
    this.form = this.fb.group({
      role: ['', Validators.required],
    });
  }

  createSearchForm() {
    this.searchForm = this.fb.group({
      search: ['', Validators.required],
    });
  }

  isAdmin() {
    if (this.userRoles !== undefined) {
      const set = new Set(this.userRoles.roles);
      return set.has(this.conferenceRoleService.getCreatorNumber()) || set.has(this.conferenceRoleService.getAdminNumber());
    }
  }

  private fetchUserRoles() {
    this.conferenceService.getUserRoles(this.conferenceId).subscribe(data => this.userRoles = data);
  }

  updateSearchData() {
    this.userService
      .getUsers(this.searchedUsers ? this.searchedUsers.number : 0,
        this.searchForm.value.search)
      .subscribe(data => {
        console.log(data);
        this.searchedUsers = data;
      });
  }

  addUser(user: BriefUserRoles) {
    this.conferenceService.inviteUser(this.conferenceId, user.username)
      .subscribe(() => this.updateUsersData());
  }


}
