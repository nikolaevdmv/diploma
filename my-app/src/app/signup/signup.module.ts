import {NgModule} from '@angular/core';
import {RouterModule} from '@angular/router';
import {SIGNUP_ROUTING} from './signup.routing';
import {SignupComponent} from './signup/signup.component';
import {SignupFormComponent} from './signup-form/signup-form.component';
import {SharedModule} from '../shared/shared.module';
import { SignupModalComponent } from './signup-modal/signup-modal.component';

@NgModule({
  imports: [
    RouterModule.forChild(SIGNUP_ROUTING),
    SharedModule
  ],
  declarations: [
    SignupComponent,
    SignupFormComponent,
    SignupModalComponent
  ],
  entryComponents: [SignupModalComponent],
  providers: [
  ]
})
export class SignupModule {
}
