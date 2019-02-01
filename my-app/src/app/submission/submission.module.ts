
import {NgModule} from '@angular/core';
import {SUBMISSION_ROUTING} from './submission.routing';
import {RouterModule} from '@angular/router';
import {CommonModule} from '@angular/common';
import {SharedModule} from '../shared/shared.module';
import {SubmissionsComponent} from './submission-list/submission-list.component';
import {SubmissionDetailComponent} from './submission-detail/submission-detail.component';
import {NewSubmissionComponent} from './new-submission/new-submission.component';
import { UserSubmissionListComponent } from './user-submission-list/user-submission-list.component';
import { ReviewerListComponent } from './reviewer-list/reviewer-list.component';
import { ReviewerListTableComponent } from './reviewer-list-table/reviewer-list-table.component';
import {SubmissionListTableComponent} from './submission-list-table/submission-list-table.component';
import { AddNewReviewerTableComponent } from './add-new-reviewer-table/add-new-reviewer-table.component';

@NgModule({
  imports: [
    RouterModule.forChild(SUBMISSION_ROUTING),
    CommonModule,
    SharedModule,
  ],
  declarations: [
    SubmissionsComponent,
    SubmissionDetailComponent,
    NewSubmissionComponent,
    UserSubmissionListComponent,
    SubmissionListTableComponent,
    ReviewerListComponent,
    ReviewerListTableComponent,
    AddNewReviewerTableComponent
  ],
  exports: [
    SubmissionsComponent,
  ]
})
export class SubmissionModule  {
}
