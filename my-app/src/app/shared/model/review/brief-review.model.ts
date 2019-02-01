import {BriefUser} from '../user/brief-user.model';

export class BriefReview {
  constructor(public id: number,
              public title: string,
              public status: number,
              public submitted: boolean,
              public author: BriefUser) {
  }
}
