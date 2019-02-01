import {NgModule} from '@angular/core';
import {AccountDetailComponent} from './account-detail/account-detail.component';
import {RouterModule} from '@angular/router';
import {ACCOUNT_ROUTING} from './account.routing';
import { AccountComponent } from './account/account.component';
import {SharedModule} from '../shared/shared.module';

@NgModule({
  imports: [
    RouterModule.forChild(ACCOUNT_ROUTING),
    SharedModule
  ],
  declarations: [AccountDetailComponent, AccountComponent],
  exports: []
})
export class AccountModule {
}
