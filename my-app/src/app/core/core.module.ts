import {NgModule} from '@angular/core';
import {FooterComponent} from './footer/footer.component';
import {NavigationComponent} from './navigation/navigation.component';

import {CommonModule} from '@angular/common';
import {SharedModule} from '../shared/shared.module';
import {ConferenceService} from './service/conference.service';
import {UploadFileService} from './service/upload-file.service';
import {RequestService} from './service/request.service';
import {SubmissionService} from './service/submission.service';
import {UserService} from './service/user.service';
import {ReviewService} from './service/review.service';
import {DocumentService} from './service/document.service';
import {CoreResolveModule} from './resolve/core-resolve.module';
import {TokenService} from './service/token.service';
import {SideComponent} from '../side/side.component';
import {ConferenceModule} from '../conference/conference.module';
import {DomService} from './service/dom.service';
import {ModalService} from './service/modal.service';
import {SigninModule} from '../signin/signin.module';
import {SignupModule} from '../signup/signup.module';
import {UtilsModule} from './service/utils/utils.module';

@NgModule({
  imports: [
    SharedModule,
    SigninModule, SignupModule,
    ConferenceModule,
    UtilsModule
  ],
  declarations: [
    FooterComponent,
    NavigationComponent,
    SideComponent
  ],
  exports: [
    FooterComponent,
    SideComponent,
    NavigationComponent,
    CoreResolveModule
  ],
  providers: [
    ConferenceService,
    UploadFileService,
    RequestService,
    SubmissionService,
    UserService,
    ReviewService,
    DocumentService,
    TokenService,

    DomService,
    ModalService
  ]
})
export class CoreModule {
}
