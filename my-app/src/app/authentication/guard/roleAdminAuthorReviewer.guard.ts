import {ActivatedRouteSnapshot, CanActivate, RouterStateSnapshot} from '@angular/router';
import {Injectable} from '@angular/core';
import {ConferenceService} from '../../core/service/conference.service';
import {SubmissionService} from '../../core/service/submission.service';
import {ConferenceRoleService} from '../../core/service/utils/conference-role.service';
import {Observable} from 'rxjs/Observable';
import {TokenService} from '../../core/service/token.service';
import {DocumentService} from '../../core/service/document.service';
import {Submission} from '../../shared/model/submission/submission.model';

@Injectable()
export class RoleAdminAuthorReviewerGuard implements CanActivate {
  constructor(private conferenceService: ConferenceService,
              private conferenceRoleService: ConferenceRoleService,
              private submissionService: SubmissionService,
              private documentService: DocumentService,
              private tokenService: TokenService) {
  }

  canActivate(route: ActivatedRouteSnapshot,
              state: RouterStateSnapshot): Observable<boolean> {
    if (route.params['submissionId']) {
      let submission: Submission;
      return this.submissionService.getSubmission(route.params['submissionId'])
        .map(data => submission = data)
        .mergeMap(() => this.conferenceService.getUserRoles(submission.conferenceId))
        .map(conference => {
          const set = new Set(conference.roles);
          return submission.author.username === this.tokenService.getUsername() ||
            set.has(this.conferenceRoleService.getCreatorNumber()) || set.has(this.conferenceRoleService.getAdminNumber()) ||
            submission.reviewers.find(value => value.username === this.tokenService.getUsername()) !== undefined;
        });
    }

    if (route.params['documentId']) {
      let submission: Submission;
      return this.documentService.getDocument(route.params['documentId'])
        .mergeMap(value => this.submissionService.getSubmission(value.submissionId))
        .map(value => {
          submission = value;
        })
        .mergeMap(() => this.conferenceService.getUserRoles(submission.conferenceId))
        .map(conference => {
          const set = new Set(conference.roles);
          return submission.author.username === this.tokenService.getUsername() ||
            set.has(this.conferenceRoleService.getCreatorNumber()) || set.has(this.conferenceRoleService.getAdminNumber()) ||
            submission.reviewers.find(value => value.username === this.tokenService.getUsername()) !== undefined;
        });

    }
  }
}
