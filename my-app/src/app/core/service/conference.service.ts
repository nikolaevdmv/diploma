import {Injectable} from '@angular/core';
import {Conference} from '../../shared/model/conference/conference.model';
import {HttpClient, HttpParams} from '@angular/common/http';
import {Observable} from 'rxjs/Observable';
import 'rxjs/add/observable/forkJoin';
import {ConferenceRequest} from '../../shared/model/conference-request/request.model';
import {RequestService} from './request.service';
import {User} from '../../shared/model/user/user.model';
import {TokenService} from './token.service';
import {Submission} from '../../shared/model/submission/submission.model';
import {BriefConference} from '../../shared/model/conference/brief-conference.model';
import {BriefSubmission} from '../../shared/model/submission/brief-submission.model';
import {BriefUser} from '../../shared/model/user/brief-user.model';
import {BriefUserRoles} from '../../shared/model/user/brief-user-roles.model';
import {Page} from '../../shared/model/paging/page.model';
import {ConferenceStatistic} from '../../shared/model/conference-statistic/conference-statistic.model';

@Injectable()
export class ConferenceService {

  url = '/api/conferences/';
  private userId;


  constructor(private http: HttpClient,
              private requestService: RequestService,
              private tokenService: TokenService) {
  }

  // createConference(request: ConferenceRequest) {
  //   return Observable.forkJoin(this.http.post('/api/conferences', request), this.requestService.update(request));
  // }


  getConferences(page: number, statusNumber?: number): Observable<Page<BriefConference>> {
    let params;
    if (statusNumber !== undefined) {
      params = new HttpParams().set('status', statusNumber.toString());
    }
    return this.http.get<Page<BriefConference>>(this.url + '?page=' + page, {params: params});
  }

  getReviewers(conferenceId: number, page: number = 0, roleNumber?: number): Observable<Page<BriefUserRoles>> {
    return this.http.get<Page<BriefUserRoles>>('/api/conferences/' + conferenceId + '/reviewers?page=' + page);
  }

  getConference(id: number | string): Observable<Conference> {
    return this.http.get<Conference>('/api/conferences/' + id);
  }

  addReviewers(conferenceId: number, submissionId: number, reviewers: Array<string>) {
    console.log(reviewers);
    return this.http.post('/api/submissions/' + submissionId + '/reviewers', reviewers);
  }

  addReviewersToConference(conferenceId: number, reviewers: Array<string>) {
    return this.http.post('/api/conferences/' + conferenceId + '/reviewers', reviewers);
  }

  addUsers(conferenceId: number, users: Array<string>) {
    return this.http.post('/api/conferences/' + conferenceId + '/users', users);
  }

  getUsers(conferenceId: number, page: number = 0, roleNumber?: number, searchString?: string): Observable<Page<BriefUserRoles>> {
    let params;
    if (roleNumber !== undefined) {
      params = new HttpParams().set('role', roleNumber.toString());
    }
    if (roleNumber !== undefined && searchString !== undefined) {
      params = new HttpParams().set('search', searchString).set('role', roleNumber.toString());
      return this.http.get<Page<BriefUserRoles>>('/api/conferences/' + conferenceId + '/users?page=' + page, {params: params});
    }
    return this.http.get<Page<BriefUserRoles>>('/api/conferences/' + conferenceId + '/users?page=' + page, {params: params});
  }

  createSubmission(value: Submission, conferenceId: number, file: File) {
    const formdata: FormData = new FormData();
    formdata.append('file', file);
    console.log(value);
    formdata.append('submission', new Blob([JSON.stringify(value)], {type: 'application/json'}));
    return this.http.post('/api/conferences/' + conferenceId + '/submissions', formdata);
  }


  getSubmissions(conferenceId: number, status: number, page: number = 0): Observable<Page<BriefSubmission>> {
    let params;
    if (status !== undefined) {
      params = new HttpParams().set('status', status.toString());
    }
    return this.http.get<Page<BriefSubmission>>('/api/conferences/' + conferenceId + '/submissions?page=' + page, {params: params});
  }

  addRolesToUser(conferenceId: number, userId: number, roles: Set<number>) {
    return this.http.put<BriefUserRoles>('/api/conferences/' + conferenceId + '/users/' + userId, Array.from(roles));
  }

  getUserSubmissions(conferenceId: number, status: number, page: number = 0): Observable<Page<BriefSubmission>> {
    let params;
    if (status !== undefined) {
      params = new HttpParams().set('status', status.toString());
    }
    return this.http.get<Page<BriefSubmission>>
    ('/api/conferences/'
      + conferenceId + '/submissions/users/'
      + this.tokenService.getUserId() + '?page=' + page, {params: params});
  }

  getReviewerSubmissions(conferenceId: number, status: number, page: number = 0): Observable<Page<BriefSubmission>> {
    let params;
    if (status !== undefined) {
      params = new HttpParams().set('status', status.toString());
    }
    return this.http.get<Page<BriefSubmission>>
    ('/api/conferences/'
      + conferenceId + '/submissions/reviewers/'
      + this.tokenService.getUserId() + '?page=' + page, {params: params});
  }

  getUserRoles(conferenceId: number): Observable<BriefUserRoles> {
    return this.http.get<BriefUserRoles>('/api/conferences/' + conferenceId + '/users/' + this.tokenService.getUserId());
  }

  inviteUser(conferenceId: number, username: string) {
    return this.http.post('/api/conferences/' + conferenceId + '/invite', username);
  }

  getStatistic(conferenceId: number): Observable<ConferenceStatistic> {
    return this.http.get<ConferenceStatistic>('/api/conferences/' + conferenceId + '/statistic');
  }
}
