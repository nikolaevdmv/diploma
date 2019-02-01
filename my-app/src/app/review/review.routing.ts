import {Routes} from '@angular/router';
import {RoleGuard} from '../authentication/guard/role.guard';
import {ReviewDetailComponent} from './review-detail/review-detail.component';
import {ReviewListComponent} from './review-list/review-list.component';

export const REVIEW_ROUTING: Routes = [
  {
    path: 'reviews',
    component: ReviewListComponent,
    canActivate: [RoleGuard],
  },
  {
    path: 'reviews/:reviewId',
    component: ReviewDetailComponent,
    canActivate: [RoleGuard],
  },
];
