import {Observable} from 'rxjs/Observable';
import {Injectable} from '@angular/core';
import {ActivatedRouteSnapshot, Resolve, RouterStateSnapshot} from '@angular/router';
import {User} from '../../../shared/model/user/user.model';
import {UserService} from '../../service/user.service';

@Injectable()
export class UserResolve implements Resolve<User> {


  constructor(private userService: UserService) {
  }

  resolve(route: ActivatedRouteSnapshot,
          state: RouterStateSnapshot): Observable<User> | Promise<User> | User {

    return this.userService.getCurrentUser();
  }

}
