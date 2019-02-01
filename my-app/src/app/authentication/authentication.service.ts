import {Injectable} from '@angular/core';
import {HttpClient} from '@angular/common/http';
import 'rxjs/add/operator/map';


@Injectable()
export class AuthenticationService {

  url = '/api/auth/';

  constructor(private http: HttpClient) {
  }

  doSignin(data) {
    return this.http.post(this.url + 'signin', data)
      .map(res => {
        localStorage.setItem('token', res['token']);
        localStorage.setItem('user', JSON.stringify(res['user']));
      });
  }

  doSignup(data) {
    console.log(data);
    return this.http.post(this.url + 'signup', data).map(res => localStorage.setItem('token', res['token']));
  }

  isLoggedIn(): boolean {
    return !!localStorage.getItem('token');
  }

  doLogout() {
    localStorage.removeItem('token');
    localStorage.removeItem('user');
  }
}
