import {User} from '../user/user.model';
import {BriefReview} from '../review/brief-review.model';

export class MyDocument {
  constructor(public id: number,
              public filename: string,
              public status: number,
              public submissionId: number,
              public reviews: Array<BriefReview>) {
              // public data: ByteString) {
  }
}
