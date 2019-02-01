import {NgModule} from '@angular/core';
import {ConferenceRoleService} from './conference-role.service';
import {SubmissionStatusService} from './submission-status.service';
import {ConferenceRequestStatusService} from './conference-request-status.service';
import {ReviewStatusService} from './review-status.service';
import {ConferenceStatusService} from './conference-status.service';

@NgModule({
  imports: [],
  declarations: [],
  exports: [],
  providers: [
    ConferenceRoleService,
    ConferenceRequestStatusService,
    SubmissionStatusService,
    ReviewStatusService,
    ConferenceStatusService
  ]
})
export class UtilsModule {
}
