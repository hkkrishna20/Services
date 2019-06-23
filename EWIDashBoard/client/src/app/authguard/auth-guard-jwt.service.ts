import { Injectable } from '@angular/core';
import { Router, CanActivate, ActivatedRouteSnapshot, RouterStateSnapshot } from '@angular/router';
import { JwtHelperService } from '@auth0/angular-jwt';

import { TOKEN_NAME } from '../service/auth.constant';
import { UserService } from '../service/user.service';
import { LoginService } from '../service/login.service';

@Injectable()
export class AuthGuard implements CanActivate {
	 // new
  redirectUrl: string;
  constructor(private router: Router,
    private userService: UserService, public jwtHelper: JwtHelperService) {
  }

  canActivate(route: ActivatedRouteSnapshot, state: RouterStateSnapshot): boolean {
    // when the user is logged in and just navigated to another route...
    //if (this.isLoggedIn) { return true; }

    // proceeds if not loggedIn or F5/page refresh 

    // Store the attempted URL for redirecting later
   // this.redirectUrl = state.url;

    if (this.jwtHelper.isTokenExpired(this.userService.accessToken)) {
      this.router.navigate(['/login']);
      return false;
    } else {
		this.redirectUrl = state.url;
		 this.router.navigate([this.redirectUrl]);
      return true;
    }
  }
}
