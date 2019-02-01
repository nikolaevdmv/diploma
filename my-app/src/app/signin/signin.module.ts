import {NgModule} from '@angular/core';
import {RouterModule} from '@angular/router';
import {SIGNIN_ROUTING} from './signin.routing';
import {SigninComponent} from './signin/signin.component';
import {SigninFormComponent} from './signin-form/signin-form.component';
import {SharedModule} from '../shared/shared.module';
import {UserService} from '../core/service/user.service';
import { SigninModalComponent } from './signin-modal/signin-modal.component';

@NgModule({
  imports: [
    RouterModule.forChild(SIGNIN_ROUTING),
    SharedModule
  ],
  declarations: [
    SigninComponent,
    SigninFormComponent,
    SigninModalComponent
  ],
  entryComponents: [
    SigninModalComponent
  ],
  providers: [
  ]
})
export class SigninModule {
}
