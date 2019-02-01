import {Routes} from '@angular/router';
import {DocumentDetailComponent} from './document-detail/document-detail.component';
import {RoleAdminAuthorReviewerGuard} from '../authentication/guard/roleAdminAuthorReviewer.guard';

export const DOCUMENT_ROUTING: Routes = [
  {
    path: 'documents/:documentId',
    component: DocumentDetailComponent,
    canActivate: [RoleAdminAuthorReviewerGuard]
  }
];
