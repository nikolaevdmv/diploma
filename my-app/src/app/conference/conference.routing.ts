import {Routes} from '@angular/router';
import {ConferenceListComponent} from './conference-list/conference-list.component';
import {ConferenceResolve} from '../core/resolve/conference/conference.resolve';
import {ConferenceDetailComponent} from './conference-detail/conference-detail.component';
import {ConferenceStatisticComponent} from './conference-statistic/conference-statistic.component';

export const CONFERENCE_ROUTING: Routes = [
  {
    path: '',
    component: ConferenceListComponent,
  },
  {
    path: 'conferences/:conferenceId',
    component: ConferenceDetailComponent,
    resolve: {
      conference: ConferenceResolve
    }
  },
  {
    path: 'conferences/:conferenceId/statistic',
    component: ConferenceStatisticComponent
  }
];
