import {RouterModule} from '@angular/router';
import {SharedModule} from '../shared/shared.module';
import {CommonModule} from '@angular/common';
import {NgModule} from '@angular/core';
import {DOCUMENT_ROUTING} from './document.routing';
import { DocumentDetailComponent } from './document-detail/document-detail.component';
import {ReviewModule} from '../review/review.module';

@NgModule({
  imports: [
    RouterModule.forChild(DOCUMENT_ROUTING),
    CommonModule,
    SharedModule,
    ReviewModule,
  ],
  declarations: [
  DocumentDetailComponent],
  exports: [
  ]
})
export class DocumentModule  {
}
