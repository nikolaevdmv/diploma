import {Injectable} from '@angular/core';

@Injectable()
export class ConferenceRoleService {

  private roles = ['Докладчик', 'Рецензент', 'Организатор', 'Админ'];
  private changeableRoles = ['Докладчик', 'Рецензент', 'Админ'];

  public getSubmitterNumber(): number {
    return 0;
  }

  public getReviewerNumber(): number {
    return 1;
  }

  public getCreatorNumber(): number {
    return 2;
  }

  public getAdminNumber(): number {
    return 3;
  }

  public getRole(roleNumber: number): string {
    switch (roleNumber) {
      case 0:
        return 'Докладчик';
      case 1:
        return 'Рецензент';
      case 2:
        return 'Организатор';
      case 3:
        return 'Админ';
    }
  }

  public getRoleNumber(role: string): number {
    switch (role) {
      case 'Докладчик':
        return 0;
      case 'Рецензент':
        return 1;
      case 'Организатор':
        return 2;
      case 'Админ':
        return 3;
      default :
        return undefined;
    }
  }

  getChangeableRoles(): string[] {
    return this.changeableRoles;
  }
}
