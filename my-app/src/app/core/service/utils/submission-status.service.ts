import {Injectable} from '@angular/core';

@Injectable()
export class SubmissionStatusService {
  private displayableStatuses = ['В рассмотрении', 'Отклонен', 'Принят'];

  public getStatus(statusNumber: number): string {
    switch (statusNumber) {
      case 0:
        return 'В рассмотрении';
      case 1:
        return 'Отклонен';
      case 2:
        return 'Принят';
      default:
        return undefined;
    }
  }

  public getStatusNumber(status: string): number {
    switch (status) {
      case this.getDicplayableStatuses()[0]:
        return 0;
      case this.getDicplayableStatuses()[1]:
        return 1;
      case this.getDicplayableStatuses()[2]:
        return 2;
      default:
        return undefined;
    }
  }

  public getPendingNumber(): number {
    return 0;
  }

  public getDeclinedNumber(): number {
    return 1;
  }

  public getAcceptedNumber(): number {
    return 2;
  }

  public getDicplayableStatuses(): string[] {
    return this.displayableStatuses;
  }
}
