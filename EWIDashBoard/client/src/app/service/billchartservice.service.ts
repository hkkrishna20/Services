
import { Injectable } from '@angular/core';
import { HttpClient, HttpHeaders } from '@angular/common/http';
import { Output, EventEmitter } from '@angular/core';
import { environment } from '../../environments/environment';
@Injectable({
  providedIn: 'root'
})
export class BillchartserviceService {
  

  @Output() change: EventEmitter<Object> = new EventEmitter();
  billPort: string;
  billHost: string;
  billProtocol: string;
  billoptions: Object;
  url: string = environment.apiBillUrl;

  contextpath: string;
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
