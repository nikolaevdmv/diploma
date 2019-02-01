import {Injectable} from '@angular/core';

@Injectable()
export class ConferenceRequestStatusService {
  public getPendingNumber(): number {
    return 0;
  }

  public getDeclinedNumber(): number {
    return 1;
  }

  public getAcceptedNumber(): number {
    return 2;
  }
}
