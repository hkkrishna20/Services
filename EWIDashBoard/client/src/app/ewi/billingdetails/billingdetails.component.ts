
import { trigger, state, style, transition, animate } from '@angular/animations';
import { NgModule, CUSTOM_ELEMENTS_SCHEMA } from '@angular/core';
import { UserService } from '../../service/user.service';
import { TOKEN_NAME, PYRAMID_ACCOUNT } from '../../service/auth.constant';
import { Component, OnInit, OnDestroy, ChangeDetectorRef, NgZone, Renderer, ElementRef, ViewChild } from '@angular/core';
import { LoginService } from '../../service/login.service';
import { Router, NavigationStart, NavigationEnd } from '@angular/router';
import { Stack } from "../../model/stack";
import { BillchartserviceService } from "../../service/billchartservice.service";
import { FormBuilder, FormGroup, Validators } from "@angular/forms";
import { FormControl } from "@angular/forms";
import { MatDatepicker } from "@angular/material/datepicker";
import { IFinancSeries, FinanceSeries } from "../../model/financeSeries"
import { MatDatepickerInputEvent } from '@angular/material/datepicker';
import { MatDatepickerModule } from '@angular/material/datepicker';
import { Moment } from "moment";
import { MAT_MOMENT_DATE_FORMATS, MomentDateAdapter } from '@angular/material-moment-adapter';
import { DateAdapter, MAT_DATE_FORMATS, MAT_DATE_LOCALE } from '@angular/material/core';

export interface SelectionItem {
  value: string;
  viewValue: string;
};
//const moment = _rollupMoment || _moment;
import * as _moment from 'moment';
// tslint:disable-next-line:no-duplicate-imports

const moment = _moment;
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
  selector: "app-ewi-billingdetails",
  templateUrl: "./billingdetails.component.html",
  styleUrls: ["./billingdetails.component.css"],
  providers: [
    { provide: DateAdapter, useClass: MomentDateAdapter, deps: [MAT_DATE_LOCALE] },
    { provide: MAT_DATE_FORMATS, useValue: MAT_MOMENT_DATE_FORMATS },
  ],
})

export class BillingdetailsComponent implements OnInit {
  serie: FinanceSeries[];
  date = new FormControl(moment(new Date()));
  xaxismonths: string[] = ["JAN", "FEB", "MAR", "APR", "MAY", "JUN", "JUL", "AUG", "SEPT", "OCT", "NOV", "DEC"];
  testString: any;
  selectedVal: string = "All";
  accountVal: string = "All";
  isLinear = false;
  space: string = " - ";
  firstFormGroup: FormGroup;
  secondFormGroup: FormGroup;
  thirdFormGroup: FormGroup;
  options: Object;
  count: number = 0;
  stack: Stack;
  name: string;
  seriesdata: any;
  monthandYear: any;
  monthandYear1: any;
  dateLabel: string = "Month and Year";
  yearss: number;

  SelectedToppingsList: string[];


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

  selecti: SelectionItem[] = [
    { value: 'Directors', viewValue: 'Directors' },
    { value: 'LOB', viewValue: 'LOB' },
    { value: 'Managers', viewValue: 'Managers' }
  ];
  account: SelectionItem[];
  constructor(private loginService: LoginService,
    private router: Router,
    changeDetectorRef: ChangeDetectorRef,
    public userService: UserService,
    private chartservice: BillchartserviceService,
     private _formBuilder: FormBuilder,
     ) {
    this.yearss = (new Date()).getFullYear();
    console.log(this.yearss);
    console.log("Here" + this.selectedVal);
    this.test();
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
  chosenYearHandler(normalizedYear: Moment) {
    const ctrlValue = this.date.value;
    ctrlValue.year(normalizedYear.year());
    this.date.setValue(ctrlValue);
  }
  // addEvent(type: string, event: MatDatepickerInputEvent<Date>) {
  //   this.date.push(`${type}: ${event.value}`);
  //   ${event.value}
  // }
  chosenMonthHandler(normlizedMonth: Moment, datepicker: MatDatepicker<Moment>) {
    const ctrlValue = this.date.value;
    ctrlValue.month(normlizedMonth.month());
    this.date.setValue(ctrlValue);
    datepicker.close();
  }
  test() {
    this.monthandYear = new Date(this.date.value.toString());
    this.yearss = this.monthandYear.getFullYear();

    this.chartservice.accounts(this.selectedVal).subscribe((data) => {
      console.log("Here" + data);
      this.account = data;
      //   this.getChartLoaded();
    });

    this.chartservice.xaxsis(this.yearss + "").subscribe((data) => {
      console.log("Here" + data);
      this.xaxismonths = data;
      //   this.getChartLoaded();
    });
    this.chartservice.fetchList(this.accountVal, this.selectedVal, this.yearss + "").subscribe((data) => {
      console.log("Here" + data);

      // this.serie = data;

      this.seriesdata = data;
      // this.options = data;
      this.getChart();
      this.options = this.testString;
      this.chartservice.billoptions = this.options;
      this.chartservice.change.emit(this.options);
    });

  }
  addEvent(type: string, event: MatDatepickerInputEvent<Date>) {
    this.monthandYear = new Date(this.date.value.toString());
    this.yearss = this.monthandYear.getFullYear();
    this.test();
  }
  lob() {
    // this.test();
    this.monthandYear = new Date(this.date.value.toString());
    this.yearss = this.monthandYear.getFullYear();
    console.log(this.yearss);
    console.log("Selected Values" + this.selectedVal);

    this.chartservice.accounts(this.selectedVal).subscribe((data) => {
      this.account = data;
      //   this.getChartLoaded();
    });

    this.chartservice.xaxsis(this.yearss + "").subscribe((data) => {
      console.log("Xaxis" + data);
      this.xaxismonths = data;
      //   this.getChartLoaded();
    });
  }

  private selectedList(selections: string[]) {
    console.info(selections);
    console.log("Here  ->" + this.accountVal);
    this.chartservice.fetchList(selections.toString(), this.selectedVal, this.yearss + "").subscribe((data) => {
      console.log("Here" + data);
      this.seriesdata = data;
      // this.options = data;
      this.getChart();
      this.options = this.testString;
      this.chartservice.billoptions = this.options;
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
        "text": "Member details by  " + this.selectedVal
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
          "stacking": "normal",
          "lineWidth": 1
        },
        "area": {
          "stacking": "normal",
          "lineColor": "#666666",
          "lineWidth": 1,
          "marker": {
            "lineWidth": 1,
            "lineColor": "#666666"
          }
        },
        "line": {
          "stacking": "normal",
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
  };

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
