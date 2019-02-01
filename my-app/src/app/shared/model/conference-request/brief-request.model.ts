import {BriefUser} from '../user/brief-user.model';

export class BriefConferenceRequest {
  constructor(public id: number,
              public title: string,
              public organizer: BriefUser,
              public status: number) {
  }
}
