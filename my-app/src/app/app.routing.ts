import {NgModule} from '@angular/core';
import {RouterModule, Routes} from '@angular/router';
import {AuthenticationGuard} from './authentication/guard/authentication.guard';


const appRoutes: Routes = [
  // {
  //   path: 'home',
  //   loadChildren: 'app/home/home.module#HomeModule'
  // },
  // {
  //   path: 'dashboard',
  //   canActivate: [AuthenticationGuard],
  //   loadChildren: 'app/dashboard/dashboard.module#DashboardModule'
  // },
  // {
  //   path: 'profile',
  //   loadChildren: 'app/account/account.module#AccountModule'
  // },
  {
    path: 'conferences',
    loadChildren: 'app/conference/conference.module#ConferenceModule'
  },
  {
    path: '',
    loadChildren: 'app/submission/submission.module#SubmissionModule',
    canActivate: [AuthenticationGuard]
  },
  {
    path: '',
    loadChildren: 'app/document/document.module#DocumentModule',
    canActivate: [AuthenticationGuard]
  },
  {
    path: '',
    loadChildren: 'app/conference-request/conference-request.module#ConferenceRequestModule',
    canActivate: [AuthenticationGuard]
  },
  {
    path: '',
    loadChildren: 'app/user/user.module#UserModule',
    canActivate: [AuthenticationGuard]
  },
  // {
  //   path: 'conferences/:conferenceId',
  //   loadChildren: 'app/review/review.module#ReviewModule',
  //   canActivate: [AuthenticationGuard]
  // },
  {
    path: '',
    loadChildren: 'app/signin/signin.module#SigninModule'
  },
  {
    path: '',
    loadChildren: 'app/signup/signup.module#SignupModule'
  },
  {
    path: '',
    redirectTo: 'conferences',
    pathMatch: 'full'
  }
];

@NgModule({
  imports: [
    RouterModule.forRoot(appRoutes)
  ],
  exports: [RouterModule]
})
export class AppRoutingModule {
}
