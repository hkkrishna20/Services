import { Component, OnInit } from '@angular/core';
import { Output, EventEmitter } from '@angular/core';
import{SowserviceService} from '../../../service/sowservice.service'
import { UserService } from '../../../service/user.service';
import { TOKEN_NAME, PYRAMID_ACCOUNT } from '../../../service/auth.constant';
import { OnDestroy, ChangeDetectorRef, NgZone, Renderer, ElementRef, ViewChild } from '@angular/core';
import { LoginService } from '../../../service/login.service';
import { Router, NavigationStart, NavigationEnd } from '@angular/router';@Component({
  selector: 'app-ewi-sowcomp-sowchart',
  templateUrl: './sowchart.component.html',
  styleUrls: ['./sowchart.component.css']
})
export class SowchartComponent implements OnInit{
  options: Object;
  ngOnInit() {
  }ngDoCheck(){

    this.testngOnChanges;
   }
   ngOnChanges(){
     this.testngOnChanges;
 }
   testngOnChanges(){
    this.chartservice.change.subscribe(isOpen => {
      this.options = isOpen;
    });
   
  }

  constructor(private loginService: LoginService,
    private router: Router,
    changeDetectorRef: ChangeDetectorRef,
    public userService: UserService, private chartservice: SowserviceService) {
       
    this.options=this.chartservice.sowoptions;  
    this.chartservice.change.subscribe(isOpen => {
      this.options = isOpen;
    });
    }

  logout() {
    this.userService.logout();
    if (!localStorage.getItem(TOKEN_NAME)) {
      this.router.navigate(['/login']);
    }
    location.reload();
  }

  checkSession() {
    if (!localStorage.getItem(TOKEN_NAME)) {
      this.router.navigate(['/login']);
    }
  }
}
