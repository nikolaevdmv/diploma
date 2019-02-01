import {ActivatedRouteSnapshot, CanActivate, RouterStateSnapshot} from '@angular/router';
import {Injectable} from '@angular/core';
import {Observable} from 'rxjs/Observable';
import {ConferenceService} from '../../core/service/conference.service';

@Injectable()
export class RoleGuard implements CanActivate {
  constructor(private conferenceService: ConferenceService) {
  }

  canActivate(route: ActivatedRouteSnapshot,
              state: RouterStateSnapshot): Observable<boolean> {
    const minRole: number = route.data['roles'];
    if (route.params['conferenceId']) {
      return this.conferenceService.getUserRoles(route.params['conferenceId'])
        .map(data => {
          const roles = new Set(data.roles);
          roles.forEach(value => {
            if (value >= minRole) {
              return true;
            }
          });
          return false;
        });
    }

  }

}
