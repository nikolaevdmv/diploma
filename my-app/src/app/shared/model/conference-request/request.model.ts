import {BriefUser} from '../user/brief-user.model';
import {ConferenceRequestComment} from './request-comment.model';

export class ConferenceRequest {
  constructor(public title: string,
              public acronym: string,
              public webPage: string,
              public organizer: BriefUser,
              public city: string,
              public country: string,
              public status: number,
              public expirationDate: Date,
              public comments?: Array<ConferenceRequestComment>,
              public id?: number) {
  }
}
