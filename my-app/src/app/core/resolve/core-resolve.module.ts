import {NgModule} from '@angular/core';
import {SubmissionResolve} from './submission.resolve';
import {UserResolve} from './user/user.resolve';
import {ConferenceResolve} from './conference/conference.resolve';
import {ConferenceRequestResolve} from './conference-request/conference-request.resolve';
import {ConferenceRequestListResolve} from './conference-request/conference-request-list.resolve';
import {UserConferenceResolve} from './user/user-conference.resolve';
import {ConferenceSubmissionsResolve} from './conference/conference-submissions.resolve';

@NgModule({
  providers: [
    SubmissionResolve,
    UserResolve,
    ConferenceResolve,
    ConferenceRequestResolve,
    ConferenceRequestListResolve,
    UserConferenceResolve,
    ConferenceSubmissionsResolve
  ]
})
export class CoreResolveModule {
}
