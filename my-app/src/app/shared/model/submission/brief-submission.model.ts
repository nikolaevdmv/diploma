import {BriefUser} from '../user/brief-user.model';

export class BriefSubmission {
  constructor(public id: number,
              public title: string,
              public author: BriefUser,
              public status: number,
              public reviewers: Array<BriefUser>) {}
}
