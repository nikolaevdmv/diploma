import {Routes} from '@angular/router';
import {SubmissionsComponent} from './submission-list/submission-list.component';
import {NewSubmissionComponent} from './new-submission/new-submission.component';
import {SubmissionDetailComponent} from './submission-detail/submission-detail.component';
import {SubmissionResolve} from '../core/resolve/submission.resolve';
import {ReviewerListComponent} from './reviewer-list/reviewer-list.component';
import {RoleAdminAuthorReviewerGuard} from '../authentication/guard/roleAdminAuthorReviewer.guard';

export const SUBMISSION_ROUTING: Routes = [
  {
    path: 'conferences/:conferenceId/submissions',
    component: SubmissionsComponent
  },
  {
    path: 'submissions/:submissionId',
    component: SubmissionDetailComponent,
    resolve: {
      submission: SubmissionResolve
    },
    canActivate: [RoleAdminAuthorReviewerGuard]
  },
  {
    path: 'conferences/:conferenceId/new-submission',
    component: NewSubmissionComponent
  },
  {
    path: 'submissions/:submissionId/reviewers',
    component: ReviewerListComponent,
    canActivate: [RoleAdminAuthorReviewerGuard]
  },
];
