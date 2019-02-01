import {BriefUser} from '../user/brief-user.model';

export class Review {
  constructor(public id: number,
              public title: string,
              public submitted: boolean,
              public evaluation: string,
              public date: Date,
              public status: number,
              public author: BriefUser) {
  }
}
