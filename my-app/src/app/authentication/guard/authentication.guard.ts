import {CanActivate, Router} from '@angular/router';
import {Injectable} from '@angular/core';
import {AuthenticationService} from '../authentication.service';

@Injectable()
export class AuthenticationGuard implements CanActivate {

  constructor(private auth: AuthenticationService,
              private router: Router) {
  }

  canActivate() {
    if (this.auth.isLoggedIn()) {
      return true;
    } else {
      this.router.navigate(['/signin']);
      return false;
    }
  }
}
