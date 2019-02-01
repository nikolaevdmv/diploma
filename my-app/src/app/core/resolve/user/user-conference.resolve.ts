import {Observable} from 'rxjs/Observable';
import {Injectable} from '@angular/core';
import {ActivatedRouteSnapshot, Resolve, RouterStateSnapshot} from '@angular/router';
import {User} from '../../../shared/model/user/user.model';
import {UserService} from '../../service/user.service';
import {Conference} from '../../../shared/model/conference/conference.model';
import {BriefConference} from '../../../shared/model/conference/brief-conference.model';

@Injectable()
export class UserConferenceResolve implements Resolve<Array<BriefConference>> {


  constructor(private userService: UserService) {
  }

  resolve(route: ActivatedRouteSnapshot,
          state: RouterStateSnapshot): Observable<Array<BriefConference>> | Promise<Array<BriefConference>> | Array<BriefConference> {

    return this.userService.getCurrentUserConferences();
  }

}
