import {ActivatedRouteSnapshot, Resolve, RouterStateSnapshot} from '@angular/router';
import {Submission} from '../../shared/model/submission/submission.model';
import {SubmissionService} from '../service/submission.service';
import {Observable} from 'rxjs/Observable';
import {Injectable} from '@angular/core';

@Injectable()
export class SubmissionResolve implements Resolve<Submission> {


  constructor(private submissionService: SubmissionService) {
  }

  resolve(route: ActivatedRouteSnapshot,
          state: RouterStateSnapshot): Observable<Submission> | Promise<Submission> | Submission {
    const submissionId = route.paramMap.get('submissionId');
    const conferenceId = route.paramMap.get('conferenceId');
    return this.submissionService.getSubmission(Number(submissionId));
  }

}
