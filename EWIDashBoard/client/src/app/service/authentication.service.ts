import {Injectable} from '@angular/core';
import {Http, Headers} from '@angular/http';
import { map } from 'rxjs/operators';
import { Cookie } from 'ng2-cookies';
import { Observable } from 'rxjs/Observable';
import 'rxjs/add/operator/catch';
import 'rxjs/add/operator/map';
import { HttpClient, HttpHeaders } from '@angular/common/http';

import {TOKEN_AUTH_PASSWORD, TOKEN_AUTH_USERNAME} from '../service/auth.constant';
import { ObservableLike } from 'rxjs';
import{environment} from '../../environments/environment';
@Injectable()
export class AuthenticationService {
  static AUTH_TOKEN = '/oauth/token';

  constructor(private http: Http,  private _http: HttpClient) {
  }
  saveToken(token) {
    var expireDate = new Date().getTime() + (1000 * token.expires_in);
    Cookie.set("access_token", token.access_token, expireDate);
    console.log('Obtained Access token');
  }

  login(username: string, password: string):Observable<any> {
    debugger;
    const body = `username=${encodeURIComponent(username)}&password=${encodeURIComponent(password)}&grant_type=password`;

    const headers = new Headers();
    headers.append('Content-Type', 'application/x-www-form-urlencoded');
    headers.append('Authorization', 'Basic ' + btoa(TOKEN_AUTH_USERNAME + ':' + TOKEN_AUTH_PASSWORD));
    headers.append("Access-Control-Allow-Origin", '*');
		headers.append("Access-Control-Allow-Credentials", "true");
		headers.append("Access-Control-Allow-Methods", "POST, GET, OPTIONS, DELETE");
		headers.append("Access-Control-Max-Age", "3600");
		headers.append("Access-Control-Allow-Headers", "Content-Type, Accept, X-Requested-With, remember-me");
    return this.http.post(AuthenticationService.AUTH_TOKEN, body, {headers});
    // ) .pipe(map(res => res.json()))
    //   .pipe(map((res: any) => {
    //     if (res.access_token) {
    //       return res.access_token;
    //     }
    //     return null;
    //   }));
  }
  getResource(resourceUrl): Observable<any> {
    var headers = new HttpHeaders({ 'Content-type': 'application/x-www-form-urlencoded; charset=utf-8', 'Authorization': 'Bearer ' + Cookie.get('access_token') });
    return this._http.get(resourceUrl, { headers: headers })
      .catch((error: any) => Observable.throw(error.json().error || 'Server error'));
  }

  checkCredentials() {
    return Cookie.check('access_token');
  }

  logout() {
    Cookie.delete('access_token');
    window.location.reload();
  }

}
