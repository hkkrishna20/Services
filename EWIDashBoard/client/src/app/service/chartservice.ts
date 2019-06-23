import { Injectable } from '@angular/core';
import { map } from 'rxjs/operators';
import { HttpClient, HttpHeaders, HttpParams } from '@angular/common/http';
import { Stack } from '../model/stack';
import { ChartReq } from '../model/chart-req';
import { Output, EventEmitter } from '@angular/core';
import { environment } from '../../environments/environment';
@Injectable({
  providedIn: 'root'
})
export class Chartservice {
  headers = new HttpHeaders({
    'security-token': 'mytoken',
    "Access-Control-Allow-Origin": "*",
    "Access-Control-Allow-Methods": "POST, GET, OPTIONS, DELETE, PUT",
    "Access-Control-Max-Age": "3600",
    "Access-Control-Allow-Headers": "Origin, X-Requested-With, Content-Type, Accept, Authorization, x-requested-with, authorization, x-auth-token, origin, content-type, accept"

  });

  @Output() change: EventEmitter<Object> = new EventEmitter();
  lineChartload: object;
  dashboardload: object;
  url: string = environment.apiEWIUrl;
  @Output() changeForLine: EventEmitter<Object> = new EventEmitter();
  @Output() showLegandchange: EventEmitter<Object> = new EventEmitter();
  @Output() stackedchange: EventEmitter<Object> = new EventEmitter();
  @Output() linedchange: EventEmitter<Object> = new EventEmitter();
  @Output() showchange: EventEmitter<Object> = new EventEmitter();
  @Output() previouspreviousFlagchange: EventEmitter<Object> = new EventEmitter();
  @Output() previouspriviousEventNamechange: EventEmitter<Object> = new EventEmitter();
  @Output() flagchange: EventEmitter<Object> = new EventEmitter();

  constructor(private http: HttpClient) {
    this.getAllBULeads();
  }

  fetchList(bu: String) {
    const headers = new HttpHeaders({
      'security-token': 'mytoken',
      'Content-Type': 'application/x-www-form-urlencoded',
      "Access-Control-Allow-Origin": '*',
      "Access-Control-Allow-Credentials": "true",
      "Access-Control-Allow-Methods": "POST, GET, OPTIONS, DELETE",
      "Access-Control-Max-Age": "3600",
      "Access-Control-Allow-Headers": "Content-Type, Accept, X-Requested-With, remember-me"
     
    });

    return this.http.post<Stack>(this.url + 'get', bu,{ headers: headers});
  }

  getAllBULeads() {
    const headers = new HttpHeaders({
      'security-token': 'mytoken',
      'Content-Type': 'application/x-www-form-urlencoded',
      "Access-Control-Allow-Origin": '*',
      "Access-Control-Allow-Credentials": "true",
      "Access-Control-Allow-Methods": "POST, GET, OPTIONS, DELETE",
      "Access-Control-Max-Age": "3600",
      "Access-Control-Allow-Headers": "Content-Type, Accept, X-Requested-With, remember-me"
     
    });

    return this.http.get<string[]>(this.url + 'getAllBULeads',{ headers: headers});
  }
  getGroupLeadsByBULead(buLead: string) {
    const headers = new HttpHeaders({
      'security-token': 'mytoken',
      'Content-Type': 'application/x-www-form-urlencoded',
      "Access-Control-Allow-Origin": '*',
      "Access-Control-Allow-Credentials": "true",
      "Access-Control-Allow-Methods": "POST, GET, OPTIONS, DELETE",
      "Access-Control-Max-Age": "3600",
      "Access-Control-Allow-Headers": "Content-Type, Accept, X-Requested-With, remember-me"
     
    });

    return this.http.post<string[]>(this.url + 'getGroupLeadsByBULead', buLead,{ headers: headers});
  }

  getAllDeptData(bu: ChartReq) {
    const headers = new HttpHeaders({
      'security-token': 'mytoken',
      'Content-Type': 'application/x-www-form-urlencoded',
      "Access-Control-Allow-Origin": '*',
      "Access-Control-Allow-Credentials": "true",
      "Access-Control-Allow-Methods": "POST, GET, OPTIONS, DELETE",
      "Access-Control-Max-Age": "3600",
      "Access-Control-Allow-Headers": "Content-Type, Accept, X-Requested-With, remember-me"
     
    });
    return this.http.post<Stack>(this.url + 'getAllDeptData', bu,{ headers: headers});
  }
  getDrillInnerData(bu: ChartReq, eventName: string) {
    const headers = new HttpHeaders({
      'security-token': 'mytoken',
      'Content-Type': 'application/x-www-form-urlencoded',
      "Access-Control-Allow-Origin": '*',
      "Access-Control-Allow-Credentials": "true",
      "Access-Control-Allow-Methods": "POST, GET, OPTIONS, DELETE",
      "Access-Control-Max-Age": "3600",
      "Access-Control-Allow-Headers": "Content-Type, Accept, X-Requested-With, remember-me"
     
    });
    return this.http.post<Stack>(this.url + 'getDrillInnerData?eventname=' + eventName, bu,{ headers: headers});
  }

  getDrillMemberInnerData(bu: ChartReq, eventName: string) {
    const headers = new HttpHeaders({
      'security-token': 'mytoken',
      'Content-Type': 'application/x-www-form-urlencoded',
      "Access-Control-Allow-Origin": '*',
      "Access-Control-Allow-Credentials": "true",
      "Access-Control-Allow-Methods": "POST, GET, OPTIONS, DELETE",
      "Access-Control-Max-Age": "3600",
      "Access-Control-Allow-Headers": "Content-Type, Accept, X-Requested-With, remember-me"
     
    });
    return this.http.post<Stack>(this.url + 'getDrillMemberInnerData?eventname=' + eventName, bu,{ headers: headers});
  }

  getDrillLineInnerData(bu: ChartReq, eventName: string) {
    const headers = new HttpHeaders({
      'security-token': 'mytoken',
      'Content-Type': 'application/x-www-form-urlencoded',
      "Access-Control-Allow-Origin": '*',
      "Access-Control-Allow-Credentials": "true",
      "Access-Control-Allow-Methods": "POST, GET, OPTIONS, DELETE",
      "Access-Control-Max-Age": "3600",
      "Access-Control-Allow-Headers": "Content-Type, Accept, X-Requested-With, remember-me"
     
    });
    return this.http.post<Stack>(this.url + 'getDrilllineInnerData?eventname=' + eventName, bu,{ headers: headers});
  }

}

