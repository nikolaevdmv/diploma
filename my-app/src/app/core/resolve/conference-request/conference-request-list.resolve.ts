import {ActivatedRouteSnapshot, Resolve, RouterStateSnapshot} from '@angular/router';
import {Injectable} from '@angular/core';
import {Observable} from 'rxjs/Observable';
import {RequestService} from '../../service/request.service';
import {TokenService} from '../../service/token.service';
import {UserService} from '../../service/user.service';
import {BriefConferenceRequest} from '../../../shared/model/conference-request/brief-request.model';
import {Page} from '../../../shared/model/paging/page.model';

@Injectable()
export class ConferenceRequestListResolve implements Resolve<Page<BriefConferenceRequest>> {


  constructor(private requstService: RequestService,
              private tokenService: TokenService,
              private userService: UserService) {
  }

  resolve(route: ActivatedRouteSnapshot,
          state: RouterStateSnapshot):
    Observable<Page<BriefConferenceRequest>> | Promise<Page<BriefConferenceRequest>> | Page<BriefConferenceRequest> {
    if (this.tokenService.isAdmin()) {
      return this.requstService.getAll();
    } else {
      return this.userService.getConferenceRequests();
    }
  }

}
