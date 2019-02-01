import {MyDocument} from '../document/my-document.model';
import {BriefUser} from '../user/brief-user.model';

export class Submission {
  constructor(public id: number,
              public title: String,
              public reviewable: boolean,
              public documents: Array<MyDocument>,
              public status: number,
              public author: BriefUser,
              public reviewers: Array<BriefUser>,
              public conferenceId: number) {
  }
}
