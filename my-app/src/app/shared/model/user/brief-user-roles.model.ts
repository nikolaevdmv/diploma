import {BriefUser} from './brief-user.model';

export class BriefUserRoles extends BriefUser {
  constructor(public username: string,
              public email: string,
              public firstname: string,
              public lastname: string,
              public id: number,
              public roles: Set<number>) {
    super(username, firstname, lastname, id, email);
  }
}
