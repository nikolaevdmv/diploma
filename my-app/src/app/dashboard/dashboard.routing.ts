import {Routes} from '@angular/router';
import {DashboardComponent} from './dashboard/dashboard.component';
import {UserConferenceResolve} from '../core/resolve/user/user-conference.resolve';

export const DASHBOARD_ROUTING: Routes = [
  {
    path: '',
    component: DashboardComponent,
    resolve: {
      conferences: UserConferenceResolve
    },
  }
];
