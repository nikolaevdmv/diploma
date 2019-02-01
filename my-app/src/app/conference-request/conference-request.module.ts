import {NgModule} from '@angular/core';
import {RouterModule} from '@angular/router';
import {CONFERENCE_REQUEST_ROUTING} from './conference-request.routing';
import {RequestListComponent} from './request-list/request-list.component';
import {NewConferenceRequestComponent} from './new-conference-request/new-conference-request.component';
import {ConferenceRequestDetailComponent} from './conference-request-detail/conference-request-detail.component';
import {NewConferenceRequestCommentComponent} from './new-conference-request-comment/new-conference-request-comment.component';
import {SharedModule} from '../shared/shared.module';
import {CommonModule} from '@angular/common';
import { ConferenceRequestCommentDetailComponent } from './conference-request-comment-detail/conference-request-comment-detail.component';
import {RequestListTableComponent} from './request-list-table/request-list-table.component';

@NgModule({
  imports: [
    RouterModule.forChild(CONFERENCE_REQUEST_ROUTING),
    CommonModule,
    SharedModule
  ],
  declarations: [
    RequestListComponent,
    NewConferenceRequestComponent,
    ConferenceRequestDetailComponent,
    NewConferenceRequestCommentComponent,
    ConferenceRequestCommentDetailComponent,
    RequestListTableComponent,
  ],
  exports: []
})
export class ConferenceRequestModule  {
}
