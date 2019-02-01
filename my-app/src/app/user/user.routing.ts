import {Routes} from '@angular/router';
import {ConferenceUserListComponent} from './conference-user-list/conference-user-list.component';


export const USER_ROUTING: Routes = [
  {
    path: 'conferences/:conferenceId/users',
    component: ConferenceUserListComponent
  }
];
