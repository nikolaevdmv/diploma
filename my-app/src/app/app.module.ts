import {BrowserModule} from '@angular/platform-browser';
import {NgModule} from '@angular/core';

import {AppRoutingModule} from './app.routing';

import {AppComponent} from './app.component';
import {CoreModule} from './core/core.module';
import {AuthenticationModule} from './authentication/authentication.module';
import {BrowserAnimationsModule} from '@angular/platform-browser/animations';
import {SharedModule} from './shared/shared.module';
import {UserRoleModalComponent} from './user/user-role-modal/user-role-modal.component';

@NgModule({
  declarations: [
    AppComponent, UserRoleModalComponent
  ],
  imports: [
    // BrowserModule,
    BrowserAnimationsModule,
    CoreModule,
    SharedModule,
    AppRoutingModule,
    AuthenticationModule,
  ],
  entryComponents: [UserRoleModalComponent],
  providers: [],
  bootstrap: [AppComponent]
})
export class AppModule {
}
