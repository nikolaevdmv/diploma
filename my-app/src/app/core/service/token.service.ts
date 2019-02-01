import {Injectable} from '@angular/core';
import * as jwt_decode from 'jwt-decode';

@Injectable()
export class TokenService {


  private getToken(): any {
    const token = localStorage.getItem('token');
    try {
      return jwt_decode(token);
    } catch (Error) {
      // console.log('errorrrrrr');
      return null;
    }
  }

  getUserId() {
    if (this.getToken() !== null) {
      return this.getToken().userId;
    }
  }

  getUsername(): string {
    if (this.getToken() !== null) {
      return this.getToken().sub;
    } else {
      return '';
    }
  }


  isAdmin(): boolean {
    if (this.getToken() !== null) {
      return this.getToken().isAdmin;
    } else {
      return false;
    }
  }
}
