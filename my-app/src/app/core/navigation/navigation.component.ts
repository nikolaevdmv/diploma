import {Component, OnInit} from '@angular/core';
import {AuthenticationService} from '../../authentication/authentication.service';
import {TokenService} from '../service/token.service';
import {ModalService} from '../service/modal.service';
import {SigninModalComponent} from '../../signin/signin-modal/signin-modal.component';
import {SignupModalComponent} from '../../signup/signup-modal/signup-modal.component';

@Component({
  selector: 'app-navigation',
  templateUrl: './navigation.component.html',
  styleUrls: ['./navigation.component.css']
})
export class NavigationComponent implements OnInit {

  constructor(private auth: AuthenticationService,
              private tokenService: TokenService,
              private modalService: ModalService) {
  }

  ngOnInit() {
  }

  logout() {
    this.auth.doLogout();
  }

  isLoggedIn() {
    return this.auth.isLoggedIn();
  }

  getUsername() {
    if (this.isLoggedIn()) {
      return this.tokenService.getUsername();
    } else {
      return '';
    }
  }

  initLoginModal() {
    if (this.modalService.isLoaded()) {
      this.modalService.destroy();
    }
    const inputs = {
      isMobile: false
    };
    this.modalService.init(SigninModalComponent, inputs, {});
  }

  initRegisterModal() {
    if (this.modalService.isLoaded()) {
      this.modalService.destroy();
    }
    const inputs = {
      isMobile: false
    };
    this.modalService.init(SignupModalComponent, inputs, {});
  }
}
