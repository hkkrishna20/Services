import { Component, OnInit } from "@angular/core";
import { Stack } from "../../model/stack";
import { SowserviceService } from "../../service/sowservice.service";
import {FormBuilder, FormGroup, Validators} from "@angular/forms";
import {FormControl} from "@angular/forms";
import {MomentDateAdapter} from "@angular/material-moment-adapter";
import {DateAdapter, MAT_DATE_FORMATS, MAT_DATE_LOCALE} from "@angular/material/core";
import {MatDatepicker} from "@angular/material/datepicker";
import {SowchartComponent} from "./sowchart/sowchart.component";
import { Output, EventEmitter } from '@angular/core';
import { NgModule, CUSTOM_ELEMENTS_SCHEMA } from '@angular/core';
import {MAT_MOMENT_DATE_FORMATS} from '@angular/material-moment-adapter';
import * as _moment from "moment";

import { Moment} from "moment";

import { UserService } from '../../service/user.service';
import { TOKEN_NAME, PYRAMID_ACCOUNT } from '../../service/auth.constant';
import { OnDestroy, ChangeDetectorRef, NgZone, Renderer, ElementRef, ViewChild } from '@angular/core';
import { LoginService } from '../../service/login.service';
import { Router, NavigationStart, NavigationEnd } from '@angular/router';
export interface SelectionItem {
  value: string;
  viewValue: string;
};

const moment =  _moment;
export const MY_FORMATS = {
  parse: {
    dateInput: "MM/YYYY",
  },
  display: {
    dateInput: "MM/YYYY",
    monthYearLabel: "MMM YYYY",
    dateA11yLabel: "LL",
    monthYearA11yLabel: "MMMM YYYY",
  },
};

@Component({
  selector: 'app-ewi-sowcomp',
  templateUrl: './sowcomp.component.html',
  styleUrls: ['./sowcomp.component.css'],
  providers: [
    {provide: DateAdapter, useClass: MomentDateAdapter, deps: [MAT_DATE_LOCALE]},
    {provide: MAT_DATE_FORMATS, useValue: MAT_MOMENT_DATE_FORMATS},
    SowchartComponent
  ]
})
export class SowcompComponent implements OnInit {
  @Output() observableEvent: EventEmitter<any> = new EventEmitter<any>();
  date = new FormControl(moment(new Date()));
    xaxismonths:string[]=     ["OCT", "NOV", "DEC","JAN", "FEB", "MAR", "APR", "MAY", "JUN", "JUL", "AUG", "SEPT"];
 	testString:any;
   selectedVal: string="All";
   accountVal:string="All";
    isLinear = false;
    space:string = " - ";
    firstFormGroup: FormGroup;
    secondFormGroup: FormGroup;
    thirdFormGroup: FormGroup;
    options: Object;
    count: number =0;
    stack: Stack;
    name:string;
    seriesdata:any;
    monthandYear:any;
    monthandYear1:any;
    dateLabel:string = "Month and Year";
    yearss:number;    
    ngOnInit() {
      this.firstFormGroup = this._formBuilder.group({
          firstCtrl: ["", Validators.required]
        });
        this.secondFormGroup = this._formBuilder.group({
          secondCtrl: ["", Validators.required]
        });
        this.thirdFormGroup = this._formBuilder.group({
          thirdCtrl: ["", Validators.required],
          fourthCtrl: ["", Validators.required]
        });
    }
      chosenYearHandler(normalizedYear: Moment) {
    const ctrlValue = this.date.value;
    ctrlValue.year(normalizedYear.year());
    this.date.setValue(ctrlValue);
  }

  chosenMonthHandler(normlizedMonth: Moment, datepicker: MatDatepicker<Moment>) {
    const ctrlValue = this.date.value;
    ctrlValue.month(normlizedMonth.month());
    this.date.setValue(ctrlValue);
    datepicker.close();
  }
    
  selecti: SelectionItem[] = [
    {value: 'Directors', viewValue: 'Directors'},
    {value: 'LOB', viewValue: 'LOB'},
    {value: 'Managers', viewValue: 'Managers'}
  ];
  account: SelectionItem[];
  constructor(private loginService: LoginService,
    private router: Router,
    changeDetectorRef: ChangeDetectorRef,
    public userService: UserService, private chartservice: SowserviceService, private _formBuilder: FormBuilder,
    sowChart:SowchartComponent) {
    this.yearss=  (new Date()).getFullYear();
    console.log(this.yearss);
   console.log("Here"+this.selectedVal);

     this.chartservice.accounts(this.selectedVal).subscribe((data)=>{
       console.log("Here"+data);
       this.account = data;
       //   this.getChartLoaded();
     });
     
   this.chartservice.fetchList(this.accountVal, this.selectedVal, this.yearss+"").subscribe((data)=>{
         console.log("Here"+data);
    
          // this.serie = data;
      
          this.seriesdata =data;
        // this.options = data;
            this.getChart();
             this.options = this.testString;
             this.chartservice.sowoptions=this.options;
             this.chartservice.change.emit(this.options);
       });
  }
   
//   years(){
//     console.log("Here"+    this.monthandYear);
//        alert("Here"+    this.monthandYear);
//		this.chartservice.lob(this.monthandYear).subscribe((data)=>{
//				alert("Here"+data)
//				this.selecti = data;
//         		this.getDummyChartLoaded();
//    });
//   }
 
  lob(){
    this.monthandYear = new Date(this.date.value.toString());
    this.yearss= this.monthandYear.getFullYear();
    
    console.log("Here"+this.selectedVal);
      this.chartservice.accounts(this.selectedVal).subscribe((data)=>{
        console.log("Here"+data);
        this.account = data;
        //   this.getChartLoaded();
      });
      
      // this.chartservice.xaxsis(this.yearss).subscribe((data)=>{
      //     console.log("Here"+data);
      //     this.xaxismonths = data;
      //     //   this.getChartLoaded();
      //   });
   }

   accounts(){
    console.log("Here"+this.accountVal);
      this.chartservice.fetchList(this.accountVal, this.selectedVal, this.yearss+"").subscribe((data)=>{
        console.log("Here"+data);
        this.seriesdata =data;
       // this.options = data;
           this.getChart();
            this.options = this.testString;
            this.chartservice.sowoptions=this.options;
            this.chartservice.change.emit(this.options);
      });
   }
   getChart() {
    this.testString = {
        "chart": {
            "type": "line",
            "width": 800,
        		 "height": 500
              },
              credits: {
           enabled: false
       },
   "title": {
       "text":  "Member details by  "+this.selectedVal
   },
   "subtitle": {
       "text": ""
   },
   "xAxis": {
       "categories": this.xaxismonths,
       "tickmarkPlacement": "on",
       "title": {
           "enabled": false
       }
   },
   "yAxis": {
       "title": {
           "text": "Members"
       }
   },
   "tooltip": {
       "split": true,
       "valueSuffix": "Members"
   },
   "plotOptions": {
        "series": {
         "lineWidth": 1
     },
       "area": {
           "lineColor": "#666666",
           "lineWidth": 1,
           "marker": {
               "lineWidth": 1,
               "lineColor": "#666666"
           }
       },
       "line": {
         
           "lineColor": "#666666",
           "lineWidth": 1,
           "marker": {
               "lineWidth": 1,
               "lineColor": "#666666"
           }
       }
   },
   "series": this.seriesdata 
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
