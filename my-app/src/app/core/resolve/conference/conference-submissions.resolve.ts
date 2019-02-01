import {ActivatedRouteSnapshot, Resolve, RouterStateSnapshot} from '@angular/router';
import {Injectable} from '@angular/core';
import {Observable} from 'rxjs/Observable';
import {Conference} from '../../../shared/model/conference/conference.model';
import {ConferenceService} from '../../service/conference.service';
import {Submission} from '../../../shared/model/submission/submission.model';
import {BriefSubmission} from '../../../shared/model/submission/brief-submission.model';
import {Page} from '../../../shared/model/paging/page.model';

@Injectable()
export class ConferenceSubmissionsResolve implements Resolve<Page<BriefSubmission>> {


  constructor(private conferenceService: ConferenceService) {
  }

  resolve(route: ActivatedRouteSnapshot,
          state: RouterStateSnapshot): Observable<Page<BriefSubmission>> | Promise<Page<BriefSubmission>> | Page<BriefSubmission> {
    const conferenceId = route.paramMap.get('conferenceId');
    return this.conferenceService.getSubmissions(Number(conferenceId), 0);
  }

}
