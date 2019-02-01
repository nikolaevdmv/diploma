import {NgModule} from '@angular/core';
import {RouterModule} from '@angular/router';
import {DASHBOARD_ROUTING} from './dashboard.routing';
import {DashboardComponent} from './dashboard/dashboard.component';
import {CommonModule} from '@angular/common';
import {SharedModule} from '../shared/shared.module';
import {SubmissionModule} from '../submission/submission.module';

@NgModule({
  imports: [
    RouterModule.forChild(DASHBOARD_ROUTING),
    CommonModule,
    SharedModule,
    SubmissionModule
  ],
  declarations: [
    DashboardComponent,
  ],
  exports: [
    DashboardComponent
  ]
})
export class DashboardModule {
}
