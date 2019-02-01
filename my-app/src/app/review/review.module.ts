import {RouterModule} from '@angular/router';
import {SharedModule} from '../shared/shared.module';
import {CommonModule} from '@angular/common';
import {NgModule} from '@angular/core';
import {REVIEW_ROUTING} from './review.routing';
import {ReviewListComponent} from './review-list/review-list.component';
import {ReviewDetailComponent} from './review-detail/review-detail.component';
import { NewReviewComponent } from './new-review/new-review.component';

@NgModule({
  imports: [
    RouterModule.forChild(REVIEW_ROUTING),
    CommonModule,
    SharedModule
  ],
  declarations: [
    ReviewListComponent,
    ReviewDetailComponent,
    NewReviewComponent
  ],
  exports: [
    NewReviewComponent,
    ReviewDetailComponent
  ]
})
export class ReviewModule  {
}
