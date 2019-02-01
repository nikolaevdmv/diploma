import {NgModule} from '@angular/core';
import {AuthenticationService} from './authentication.service';
import {HttpClientModule} from '@angular/common/http';
import {AuthenticationGuard} from './guard/authentication.guard';
import {JwtModule} from '@auth0/angular-jwt';
import {RoleGuard} from './guard/role.guard';
import {RoleAdminAuthorReviewerGuard} from './guard/roleAdminAuthorReviewer.guard';

@NgModule({
  imports: [
    HttpClientModule,
    JwtModule.forRoot({
      config: {
        tokenGetter: () => {
          return localStorage.getItem('token');
        }
      }
    })
  ],
  providers: [
    AuthenticationService,
    HttpClientModule,
    AuthenticationGuard,
    RoleGuard,
    RoleAdminAuthorReviewerGuard
  ]
})
export class AuthenticationModule {
}
