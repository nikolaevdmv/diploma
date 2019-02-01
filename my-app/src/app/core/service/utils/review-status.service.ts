import {Injectable} from '@angular/core';

@Injectable()
export class ReviewStatusService {

  private displayableStatuses = ['Принять', 'Принять с изменениями', 'Не могу решить', 'Отклонить с изменениями', 'Отклонить'];

  public getStatus(statusNumber: number): string {
    switch (statusNumber) {
      case 0:
        return 'Отклонить';
      case 1:
        return 'Отклонить с изменениями';
      case 2:
        return 'Не могу решить';
      case 3:
        return 'Принять с изменениями';
      case 4:
        return 'Принять';
      default:
        return undefined;
    }
  }

  public getStatusNumber(status: string): number {
    switch (status) {
      case 'Отклонить':
        return 0;
      case 'Отклонить с изменениями':
        return 1;
      case 'Не могу решить':
        return 2;
      case 'Принять с изменениями':
        return 3;
      case 'Принять':
        return 4;
      default:
        return undefined;
    }
  }

  public getDicplayableStatuses(): string[] {
    return this.displayableStatuses;
  }
}
