import { Component, OnInit } from '@angular/core';
import { BillchartserviceService } from '../../../service/billchartservice.service';
import { NgModule, CUSTOM_ELEMENTS_SCHEMA } from '@angular/core';
import { UserService } from '../../../service/user.service';
import { TOKEN_NAME, PYRAMID_ACCOUNT } from '../../../service/auth.constant';
import { OnDestroy, ChangeDetectorRef, NgZone, Renderer, ElementRef, ViewChild } from '@angular/core';
import { LoginService } from '../../../service/login.service';
import { Router, NavigationStart, NavigationEnd } from '@angular/router';@Component({
  selector: 'app-ewi-billingdetails-billchart',
  templateUrl: './billchart.component.html',
  styleUrls: ['./billchart.component.css']
})
export class BillchartComponent implements OnInit {
  options: Object;

  constructor(private chartservice: BillchartserviceService,
    private loginService: LoginService,
    private router: Router,
    changeDetectorRef: ChangeDetectorRef,
    public userService: UserService) {
    this.options=this.chartservice.billoptions;
    this.chartservice.change.subscribe(isOpen => {
      this.options = isOpen;
    });
   
    
   }
   ngDoCheck(){

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

  ngOnInit() {
  }

  ngOnDestroy() {
    //prevent memory leak when component destroyed
  
  
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
