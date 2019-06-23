import { Injectable } from '@angular/core';
import { Http, Headers } from "@angular/http";
import {environment} from '../../environments/environment'
@Injectable({
  providedIn: 'root'
})
export class LoginService {
  authService: any=false;

  constructor(private http: Http) {}

  sendCredential(username: string, password: string) {
    alert("sendCredential");
    debugger;

    let url = environment.apiEWIUrl+"token";
    let encodedCredentials = btoa(username+":"+password); //method to encript string base64
    let basicHeader = "Basic " + encodedCredentials;
    let headers = new Headers({
      'Content-Type' : 'application/x-www-form-urlencoded',
      'Authorization' : basicHeader
    });

    return this.http.get(url, {headers: headers});
  } 

  checkSession()
  {
    let url = environment.apiEWIUrl+"checkSession";
    let headers = new Headers({
      'x-auth-token' : localStorage.getItem('xAuthToken')
    });

    return this.http.get(url, {headers: headers});
  }

  logout()
  {
    let url = environment.apiEWIUrl+"user/logout";
    
    let headers = new Headers({
      'x-auth-token' : localStorage.getItem('xAuthToken')
    });
    return this.http.post(url, '', {headers: headers});
  }
}
