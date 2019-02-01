import {NgModule} from '@angular/core';
import {SharedModule} from '../shared/shared.module';
import {USER_ROUTING} from './user.routing';
import {RouterModule} from '@angular/router';
import {ConferenceUserListComponent} from './conference-user-list/conference-user-list.component';
import {AddUserRoleComponent} from './add-user-role/add-user-role.component';
import {ConferenceUsersTableComponent} from './conference-users-table/conference-users-table.component';
import {UserRoleModalComponent} from './user-role-modal/user-role-modal.component';

@NgModule({
  imports: [
    RouterModule.forChild(USER_ROUTING),
    SharedModule
  ],
  declarations: [ConferenceUserListComponent, AddUserRoleComponent, ConferenceUsersTableComponent],
  exports: [],
  entryComponents: [AddUserRoleComponent]
})
export class UserModule {
}
