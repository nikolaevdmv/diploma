import {Injectable} from '@angular/core';

@Injectable()
export class ConferenceStatusService {
  private displayableStatuses = ['Все', 'Активные', 'Завершенные'];

  public getDicplayableStatuses(): string[] {
    return this.displayableStatuses;
  }

  public getStatusNumber(status: string) {
    switch (status) {
      case this.getDicplayableStatuses()[0]:
        return undefined;
      case this.getDicplayableStatuses()[1]:
        return 0;
      case this.getDicplayableStatuses()[2]:
        return 1;
      default:
        return undefined;
    }
  }
}
