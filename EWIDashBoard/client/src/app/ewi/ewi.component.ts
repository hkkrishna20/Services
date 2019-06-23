
import { trigger, state, style, transition, animate } from '@angular/animations';
import { NgModule, CUSTOM_ELEMENTS_SCHEMA } from '@angular/core';
import { UserService } from '../service/user.service';
import { TOKEN_NAME, PYRAMID_ACCOUNT } from '../service/auth.constant';
import { Component, OnInit, OnDestroy, ChangeDetectorRef, NgZone, Renderer, ElementRef, ViewChild } from '@angular/core';
import { LoginService } from '../service/login.service';
import { Router, NavigationStart, NavigationEnd } from '@angular/router';
@Component({
  selector: 'app-ewi',
  templateUrl: './ewi.component.html',
  styleUrls: ['./ewi.component.css'],
  animations: [
    trigger('flipState', [
      state('active', style({
        transform: 'rotateY(179deg)'
      })),
      state('inactive', style({
        transform: 'rotateY(0)'
      })),
      transition('active => inactive', animate('500ms ease-out')),
      transition('inactive => active', animate('500ms ease-in'))
    ])
  ]
})
export class EwiComponent implements OnInit {
  flip: string = 'inactive';
  flipBill: string = 'inactive';

  flipSow: string = 'inactive';

  flipEWI: string = 'inactive';
  breakpoint: number;

  constructor(private loginService: LoginService,
    private router: Router,
    changeDetectorRef: ChangeDetectorRef,
    public userService: UserService) { 

      setInterval(() => { this.checkSession(); } , 1000);

    }

  ngOnInit() {
    this.breakpoint = (window.innerWidth <= 400) ? 1 : 6;
  }
  onResize(event) {
    this.breakpoint = (event.target.innerWidth <= 400) ? 1 : 6;
  }
  colval:number=6;
  colval2:number=6;
  showDivi = true;
  showDivider = true;
  showsowDivider = true;
  device:number=0;	 
  
  toggleFlip() {
    this.flip = (this.flip == 'inactive') ? 'active' : 'inactive';
  } 
  toggleFlipSow() {
    this.flipSow = (this.flipSow == 'inactive') ? 'active' : 'inactive';
  } 
  toggleFlipEWI() {
    this.flipEWI = (this.flipEWI == 'inactive') ? 'active' : 'inactive';
  } 
  toggleFlipBill() {
    this.flipBill = (this.flipBill == 'inactive') ? 'active' : 'inactive';
  } 
  onChange(value) {
        if (value.checked == true) {
          this.device = 1;
               this.showDivider = true;
               if(this.showDivi ==  false){
                this.colval=0;
                this.colval2=12;
             }
       if(this.showDivi ==true && this.showDivider == true){
                this.colval=5;
                this.colval2=7;
          }
        } else {
              this.device = 0;
               this.showDivider = false;
               if(!this.showDivi){
                this.colval=0;
                this.colval2=12;

               }
               
               if(this.showDivi && this.showDivider){
                this.colval=5;
                this.colval2=7;

               }
        }
    }
   
    onChangeewi(value) {
        if (value.checked == true) {
          this.device = 1;
               this.showDivi = true;
               if(!this.showDivider){
                this.colval=12;
                this.colval2=0;
               }
               
               if(this.showDivi && this.showDivider){
                this.colval=5;
                this.colval2=7;

               }
        } else {
          this.device = 0;
               this.showDivi = false;
               if(!this.showDivider){
                this.colval=12;
                this.colval2=0;

               }

               if(this.showDivi && this.showDivider){
                this.colval=5;
                this.colval2=7;

               }
              }
    }



    onChangesow(value) {
    
      if (value.checked == true) {
       
        this.device = 1;
        this.showsowDivider = true;
             if(!this.showsowDivider){
              this.colval=12;
              this.colval2=0;
             }
             
             if(this.showDivi && this.showsowDivider){
              this.colval=5;
              this.colval2=7;

             }
      } else {
        this.device = 0;
             this.showDivi = false;
             this.showsowDivider= false;
             if(!this.showsowDivider){
              this.colval=12;
              this.colval2=0;

             }

             if(this.showDivi && this.showsowDivider){
              this.colval=5;
              this.colval2=7;
             }
            }
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
