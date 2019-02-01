import {ActivatedRouteSnapshot, Resolve, RouterStateSnapshot} from '@angular/router';
import {Injectable} from '@angular/core';
import {Observable} from 'rxjs/Observable';
import {ConferenceRequest} from '../../../shared/model/conference-request/request.model';
import {RequestService} from '../../service/request.service';

@Injectable()
export class ConferenceRequestResolve implements Resolve<ConferenceRequest> {


  constructor(private requstService: RequestService) {
  }

  resolve(route: ActivatedRouteSnapshot,
          state: RouterStateSnapshot): Observable<ConferenceRequest> | Promise<ConferenceRequest> | ConferenceRequest {
    const requestId = route.paramMap.get('conferenceRequestId');
    return this.requstService.getRequest(requestId);
  }

}
