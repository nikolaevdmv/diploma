import {BriefUser} from '../user/brief-user.model';
import {BriefSubmission} from '../submission/brief-submission.model';

export class Conference {
  constructor(public id: number,
              public title: string,
              public acronym: string,
              public webPage: string,
              public expirationDate: Date,
              public city: string,
              public country: string,
              public submissions: Array<BriefSubmission>,
              public reviewers: Array<BriefUser>,
              public organizer: BriefUser
              ) {
  }
}
