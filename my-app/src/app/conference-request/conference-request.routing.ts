import {Routes} from '@angular/router';
import {ConferenceRequestResolve} from '../core/resolve/conference-request/conference-request.resolve';
import {NewConferenceRequestComponent} from './new-conference-request/new-conference-request.component';
import {ConferenceRequestListResolve} from '../core/resolve/conference-request/conference-request-list.resolve';
import {RequestListComponent} from './request-list/request-list.component';
import {ConferenceRequestDetailComponent} from './conference-request-detail/conference-request-detail.component';

export const CONFERENCE_REQUEST_ROUTING: Routes = [
  {
    path: 'new-conference-request',
    component: NewConferenceRequestComponent
  },
  {
    path: 'conference-requests',
    component: RequestListComponent,
    resolve: {
      conferenceRequestList: ConferenceRequestListResolve
    }
  },
  {
    path: 'conference-requests/:conferenceRequestId',
    component: ConferenceRequestDetailComponent,
    resolve: {
      conferenceRequest: ConferenceRequestResolve
    }
  }
];
