import {Injectable} from '@angular/core';
import {HttpClient, HttpParams} from '@angular/common/http';
import {User} from '../../shared/model/user/user.model';
import {Conference} from '../../shared/model/conference/conference.model';
import {Observable} from 'rxjs/Observable';
import {Review} from '../../shared/model/review/review.model';
import {TokenService} from './token.service';
import {Submission} from '../../shared/model/submission/submission.model';
import {ConferenceRequest} from '../../shared/model/conference-request/request.model';
import {BriefConference} from '../../shared/model/conference/brief-conference.model';
import {BriefConferenceRequest} from '../../shared/model/conference-request/brief-request.model';
import {BriefSubmission} from '../../shared/model/submission/brief-submission.model';
import {Page} from '../../shared/model/paging/page.model';
import {BriefUser} from '../../shared/model/user/brief-user.model';

@Injectable()
export class UserService {

  private url = '/api/users/';
  private userId;

  private reviewer: Map<number, boolean>;

  constructor(private http: HttpClient, private tokenService: TokenService) {
    this.reviewer = new Map();
  }

  getCurrentUser(): Observable<User> {
    return this.http.get<User>('/api/user');

  }

  getReviewableConferences(): Observable<Map<number, boolean>> {
    this.userId = this.tokenService.getUserId();
    const params = new HttpParams().set('reviewable', true.toString());
    return this.http.get<Array<Conference>>(this.url + this.userId + '/conferences', {params: params}).map(conferences => {
      conferences.forEach(c => this.reviewer.set(c.id, true));
      return this.reviewer;
    });
  }


  isReviewer(conferenceId: number): Observable<boolean> {
    this.userId = this.tokenService.getUserId();
    const params = new HttpParams().set('conferenceId', conferenceId.toString());
    return this.http.get<boolean>(this.url + this.userId + '/reviewer', {params: params});
  }


  getReviewList(conferenceId: number): Observable<Array<Review>> {
    this.userId = this.tokenService.getUserId();
    const params = new HttpParams().set('conferenceId', conferenceId.toString());
    return this.http.get<Array<Review>>(this.url + this.userId + '/reviews', {params: params});
  }

  getCurrentUserConferences(): Observable<Array<BriefConference>> {
    this.userId = this.tokenService.getUserId();
    return this.http.get<Array<BriefConference>>(this.url + this.userId + '/conferences');
  }

  getConferenceSubmissions(conferenceId: number): Observable<Array<Submission>> {
    this.userId = this.tokenService.getUserId();
    return this.http.get<Array<Submission>>(this.url + this.userId + '/conferences/' + conferenceId + '/submissions');
  }

  getSubmissions(): Observable<Array<BriefSubmission>> {
    this.userId = this.tokenService.getUserId();
    return this.http.get<Array<BriefSubmission>>(this.url + this.userId + '/submissions');
  }

  getUsers(page: number = 0, searchString?: string): Observable<Page<BriefUser>> {
    let params;
    if (searchString !== undefined) {
      params = new HttpParams().set('search', searchString);
    }
    return this.http.get<Page<BriefUser>>(this.url + '?page=' + page, {params: params});
  }

  getConferenceRequests(page: number = 0, statusNumber?: number): Observable<Page<BriefConferenceRequest>> {
    let params;
    if (statusNumber !== undefined ) {
      params = new HttpParams().set('status', statusNumber.toString());
    }
    this.userId = this.tokenService.getUserId();
    return this.http.get<Page<BriefConferenceRequest>>(this.url + this.userId + '/conference-requests?page=' + page, {params: params});
  }

}
