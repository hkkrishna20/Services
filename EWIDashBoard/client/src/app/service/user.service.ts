import {Injectable} from '@angular/core';
import {JwtHelperService} from '@auth0/angular-jwt';

import {TOKEN_NAME, PYRAMID_ACCOUNT} from '../service/auth.constant';

@Injectable()
export class UserService {
  jwtHelper: JwtHelperService = new JwtHelperService();
  accessToken: string;
  isAdmin: boolean;

  constructor() { }

  login(accessToken: string) {
	  debugger;
    const decodedToken = this.jwtHelper.decodeToken(accessToken);
    console.log(decodedToken);
    // this.isAdmin = decodedToken.authorities.some(el => el === 'ADMIN_USER');
    this.accessToken = accessToken;

    localStorage.setItem(PYRAMID_ACCOUNT, decodedToken.authorities);
    localStorage.setItem(TOKEN_NAME, accessToken);
	alert("inside userservice login"+decodedToken);
  }
isValid(){
	if(this.accessToken !=null){
		return true;
	}
	return false;
}

  logout() {
    this.accessToken = null;
    this.isAdmin = false;
    localStorage.removeItem(TOKEN_NAME);
    localStorage.removeItem(PYRAMID_ACCOUNT);
  }

  isAdminUser(): boolean {
    return this.isAdmin;
  }

  isUser(): boolean {
    return this.accessToken && !this.isAdmin;
  }
}
