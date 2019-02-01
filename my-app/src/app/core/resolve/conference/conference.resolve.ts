import {ActivatedRouteSnapshot, Resolve, RouterStateSnapshot} from '@angular/router';
import {Injectable} from '@angular/core';
import {Observable} from 'rxjs/Observable';
import {Conference} from '../../../shared/model/conference/conference.model';
import {ConferenceService} from '../../service/conference.service';

@Injectable()
export class ConferenceResolve implements Resolve<Conference> {


  constructor(private conferenceService: ConferenceService) {
  }

  resolve(route: ActivatedRouteSnapshot,
          state: RouterStateSnapshot): Observable<Conference> | Promise<Conference> | Conference {
    const conferenceId = route.paramMap.get('conferenceId');
    return this.conferenceService.getConference(conferenceId);
  }

}
