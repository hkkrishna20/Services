import { Injectable } from '@angular/core';
import { LoginService } from '../service/login.service';
import { CanActivate, Router, ActivatedRouteSnapshot, RouterStateSnapshot } from '@angular/router';
import { UserService } from '../service/user.service';


@Injectable({
  providedIn: 'root'
})

export class AuthGuardService implements CanActivate {
  redirectUrl: string;
  constructor(private userService: UserService, private router: Router) {}
   
   canActivate(route: ActivatedRouteSnapshot, state: RouterStateSnapshot) {
    if (this.userService.isValid()) {
		this.redirectUrl = state.url;
		this.router.navigate([this.redirectUrl]);
      return true;
    } else {
      this.router.navigate(['/login'], {
        queryParams: {
          return: state.url
        }
      });	
      return false;
    }
  }
}
