import { Injectable } from '@angular/core';
import { HttpClient, HttpHeaders, HttpParams } from '@angular/common/http';
import { Output, EventEmitter } from '@angular/core';
import { environment } from '../../environments/environment';

@Injectable({
  providedIn: 'root'
})
export class SowserviceService {
  headers = new HttpHeaders({
    'security-token': 'mytoken',
    "Access-Control-Allow-Origin": "*",
    "Access-Control-Allow-Methods": "POST, GET, OPTIONS, DELETE, PUT",
    "Access-Control-Max-Age": "3600",
    "Access-Control-Allow-Headers": "Origin, X-Requested-With, Content-Type, Accept, Authorization, x-requested-with, authorization, x-auth-token, origin, content-type, accept"

  });

  @Output() change: EventEmitter<Object> = new EventEmitter();
  sowPort: string;
  sowHost: string;
  sowProtocol: string;
  sowoptions: Object;
  url: string = environment.apiSowUrl;


  constructor(private http: HttpClient) {

  }
  fetchList(account: string, lob: string, month: string) {
    const headers = new HttpHeaders({
      'security-token': 'mytoken',
      'Content-Type': 'application/x-www-form-urlencoded',
      "Access-Control-Allow-Origin": '*',
      "Access-Control-Allow-Credentials": "true",
      "Access-Control-Allow-Methods": "POST, GET, OPTIONS, DELETE",
      "Access-Control-Max-Age": "3600",
      "Access-Control-Allow-Headers": "Content-Type, Accept, X-Requested-With, remember-me"
     
    });

    return this.http.get<any>(this.url + 'getchart/' + account + '/' + lob + '/' + month,{ headers: headers});
  }
  xaxsis(monthAndyear: string) {
    const headers = new HttpHeaders({
      'security-token': 'mytoken',
      'Content-Type': 'application/x-www-form-urlencoded',
      "Access-Control-Allow-Origin": '*',
      "Access-Control-Allow-Credentials": "true",
      "Access-Control-Allow-Methods": "POST, GET, OPTIONS, DELETE",
      "Access-Control-Max-Age": "3600",
      "Access-Control-Allow-Headers": "Content-Type, Accept, X-Requested-With, remember-me"
     
    });
    return this.http.get<any>(this.url + 'year/' + monthAndyear,{ headers: headers});
  }

  lob(monthAndyear: string) {
    const headers = new HttpHeaders({
      'security-token': 'mytoken',
      'Content-Type': 'application/x-www-form-urlencoded',
      "Access-Control-Allow-Origin": '*',
      "Access-Control-Allow-Credentials": "true",
      "Access-Control-Allow-Methods": "POST, GET, OPTIONS, DELETE",
      "Access-Control-Max-Age": "3600",
      "Access-Control-Allow-Headers": "Content-Type, Accept, X-Requested-With, remember-me"
     
    });
    return this.http.get<any>(this.url + 'lob/' + monthAndyear,{ headers: headers});
  }
  accounts(lob: string) {
    const headers = new HttpHeaders({
      'security-token': 'mytoken',
      'Content-Type': 'application/x-www-form-urlencoded',
      "Access-Control-Allow-Origin": '*',
      "Access-Control-Allow-Credentials": "true",
      "Access-Control-Allow-Methods": "POST, GET, OPTIONS, DELETE",
      "Access-Control-Max-Age": "3600",
      "Access-Control-Allow-Headers": "Content-Type, Accept, X-Requested-With, remember-me"
     
    });
    return this.http.get<any>(this.url + 'accounts/' + lob,{ headers: headers});
  }
}
