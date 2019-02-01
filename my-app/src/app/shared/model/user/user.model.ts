import {Authority} from './authority.enum';
import {Conference} from '../conference/conference.model';

export class User {
  constructor(public id: number,
              public username: string,
              public firstname: string,
              public lastname: string,
              public email: string,
              public enabled: boolean
              // public authorities: Array<Authority>,
              // public conferences: Array<Conference>
  ) {
  }
}
