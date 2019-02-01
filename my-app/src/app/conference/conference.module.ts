import {NgModule} from '@angular/core';
import {ConferenceDetailComponent} from './conference-detail/conference-detail.component';
import {RouterModule} from '@angular/router';
import {CONFERENCE_ROUTING} from './conference.routing';
import {ConferenceListComponent} from './conference-list/conference-list.component';
import {UserConferenceListComponent} from './user-conference-list/user-conference-list.component';
import {SharedModule} from '../shared/shared.module';
import {CommonModule} from '@angular/common';
import {ConferenceListTableComponent} from './conference-list-table/conference-list-table.component';
import { ConferenceStatisticComponent } from './conference-statistic/conference-statistic.component';

@NgModule({
  imports: [
    RouterModule.forChild(CONFERENCE_ROUTING),
    CommonModule,
    SharedModule,
  ],
  declarations: [
    ConferenceDetailComponent,
    ConferenceListComponent,
    UserConferenceListComponent,
    ConferenceListTableComponent,
    ConferenceStatisticComponent
  ],
  exports: [
    UserConferenceListComponent
  ]
})
export class ConferenceModule {
}
