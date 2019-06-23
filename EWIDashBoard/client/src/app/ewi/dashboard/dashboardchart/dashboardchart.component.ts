import { Component, OnInit } from '@angular/core';
import { Stack } from '../../../model/stack';
import { FormBuilder, FormGroup, Validators } from '@angular/forms';
import { FormControl } from '@angular/forms';
import { MomentDateAdapter } from '@angular/material-moment-adapter';
import { DateAdapter, MAT_DATE_FORMATS, MAT_DATE_LOCALE } from '@angular/material/core';
import { MatDatepicker } from '@angular/material/datepicker';
import { ChartReq } from '../../../model/chart-req';
import { UserService } from '../../../service/user.service';
import { TOKEN_NAME, PYRAMID_ACCOUNT } from '../../../service/auth.constant';
import { OnDestroy, ChangeDetectorRef, NgZone, Renderer, ElementRef, ViewChild } from '@angular/core';
import { LoginService } from '../../../service/login.service';
import { Router, NavigationStart, NavigationEnd } from '@angular/router';
import { NgModule, CUSTOM_ELEMENTS_SCHEMA } from '@angular/core';
import * as _moment from 'moment';
import { defaultFormatUtc as _rollupMoment, Moment } from 'moment';
import { Chartservice } from '../../../service/chartservice';

const moment = _moment;
const moment1 = _moment;

export const MY_FORMATS = {
    parse: {
        dateInput: 'MM/YYYY',
    },
    display: {
        dateInput: 'MM/YYYY',
        monthYearLabel: 'MMM YYYY',
        dateA11yLabel: 'LL',
        monthYearA11yLabel: 'MMMM YYYY',
    },
};

@Component({
    selector: 'app-ewi-dashboard-dashboardchart',
    templateUrl: './dashboardchart.component.html',
    styleUrls: ['./dashboardchart.component.css'],
    providers: [
        { provide: DateAdapter, useClass: MomentDateAdapter, deps: [MAT_DATE_LOCALE] },
        { provide: MAT_DATE_FORMATS, useValue: MY_FORMATS },
    ],

})
export class DashboardchartComponent implements OnInit {
    date = new FormControl(moment());
    date1 = new FormControl(moment1());
    chartReq: ChartReq;
    backEvent: string[] = new Array();
    legendnames: string[] = new Array();
    flag: number = 0;
    previousFlag: number = 0;
    priviousEventName: string;

    previouspreviousFlag: number = 0;
    previouspriviousEventName: string;

    show: boolean;
    showLegand: boolean;



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

    chosenYearHandler1(normalizedYear: Moment) {
        const ctrlValue = this.date.value;
        ctrlValue.year(normalizedYear.year());
        this.date1.setValue(ctrlValue);
    }

    chosenMonthHandler1(normlizedMonth: Moment, datepicker1: MatDatepicker<Moment>) {
        const ctrlValue = this.date.value;
        ctrlValue.month(normlizedMonth.month());
        this.date1.setValue(ctrlValue);
        datepicker1.close();
    }

    buLeadLable: string = 'BU Lead';
    groupLeadLable: string = 'Group Lead';
    monthandYear: any;
    monthandYear1: any;
    dateLabel: string = "Month and Year";

    buLeadLableF() {
        this.buLeadLable = this.firstFormGroup.controls.firstCtrl.value;
        this.getGroupLeadsByBULead(this.buLeadLable);

    }

    groupLeadLableF() {
        this.groupLeadLable = this.secondFormGroup.controls.secondCtrl.value;
    }


    firstFormGroup: FormGroup;
    secondFormGroup: FormGroup;
    thirdFormGroup: FormGroup;
    options: object;
    optionsForLine: object;
    stack: Stack;
    count: number = 0;
    isLinear = false;
    stacked = true;
    lined = false;
    space: string = " - ";
    name: string;


    ngOnInit() {
        this.legendnames = [];
        this.getAllBULeads();
        this.firstFormGroup = this._formBuilder.group({
            firstCtrl: ['', Validators.required]
        });
        this.secondFormGroup = this._formBuilder.group({
            secondCtrl: ['', Validators.required]
        });
        this.thirdFormGroup = this._formBuilder.group({
            thirdCtrl: ['', Validators.required],
            fourthCtrl: ['', Validators.required]
        });
    }
    ngDoCheck() {

        this.testngOnChanges;
    }
    ngOnChanges() {
        this.testngOnChanges;
    }
    testngOnChanges() {
        this.chartservice.stackedchange.subscribe(isOpen => {
            this.stacked = isOpen;
        });
        this.chartservice.linedchange.subscribe(isOpen => { this.lined = isOpen; });
        this.chartservice.change.subscribe(isOpen => { this.options = isOpen; });
        this.chartservice.showLegandchange.subscribe(isOpen => { this.showLegand = isOpen; });
        this.chartservice.showchange.subscribe(isOpen => { this.show = isOpen; });
        this.chartservice.previouspreviousFlagchange.subscribe(isOpen => { this.previouspreviousFlag = isOpen; });
        this.chartservice.previouspriviousEventNamechange.subscribe(isOpen => { this.previouspriviousEventName = isOpen; });
        this.chartservice.flagchange.subscribe(isOpen => { this.flag = isOpen; });
        this.chartservice.changeForLine.subscribe(isOpen => { this.optionsForLine = isOpen; });
    }
    constructor(private loginService: LoginService,
        private router: Router,
        changeDetectorRef: ChangeDetectorRef,
        public userService: UserService, private chartservice: Chartservice, private _formBuilder: FormBuilder) {


        this.chartservice.stackedchange.subscribe(isOpen => {
            this.stacked = isOpen;
        });
        this.chartservice.linedchange.subscribe(isOpen => { this.lined = isOpen; });
        this.chartservice.change.subscribe(isOpen => { this.options = isOpen; });
        this.chartservice.showLegandchange.subscribe(isOpen => { this.showLegand = isOpen; });
        this.chartservice.showchange.subscribe(isOpen => { this.show = isOpen; });
        this.chartservice.previouspreviousFlagchange.subscribe(isOpen => { this.previouspreviousFlag = isOpen; });
        this.chartservice.previouspriviousEventNamechange.subscribe(isOpen => { this.previouspriviousEventName = isOpen; });
        this.chartservice.flagchange.subscribe(isOpen => { this.flag = isOpen; });
        this.chartservice.changeForLine.subscribe(isOpen => { this.optionsForLine = isOpen; });
    }

    test() {
        this.flag = 0;
        this.monthandYear = moment(new Date(this.date.value.toString())).format('MM/YYYY');
        this.monthandYear1 = moment(new Date(this.date1.value.toString())).format('MM/YYYY');
        this.dateLabel = '';
        this.chartReq = new ChartReq();
        this.chartReq.buLead = this.firstFormGroup.controls.firstCtrl.value;
        this.chartReq.groupLead = this.secondFormGroup.controls.secondCtrl.value;
        this.chartReq.fromDate = this.date.value;
        this.chartReq.toDate = this.date1.value;
        this.chartReq.dateInterval = this.monthDiff(new Date(this.date.value.toString()), new Date(this.date1.value.toString()));

        this.chartservice.getAllDeptData(this.chartReq).subscribe((data) => {
            this.stack = data;
            this.getChartLoaded();
            this.showLegand = true;
        });
    }

    monthDiff(d1: any, d2: any) {
        var oneDay = 24 * 3600 * 1000;
        for (var d = [], ms = d1 * 1, last = d2 * 1; ms <= last; ms += oneDay) {
            d.push(moment(new Date(ms)).format('YYYYMM'));
        }
        return d;
    }

    getChartLoaded() {

        this.options = {
            chart: {
                "width": 800,
                "height": 500

            },
            title: {
                text: 'CGI EWI , Grouped by Month',
            },
            credits:
            {
                enabled: false,
            },

            xAxis: {
                categories: this.stack.dynamicXaxi
            },

            yAxis: {

                allowDecimals: false,
                min: 0,
                title: {
                    text: 'Number of Members'
                },
                stackLabels: {

                    enabled: true,
                    style: {
                        fontWeight: 'bold',
                        color: 'gray'
                    },
                    formatter: function () {
                        return this.stack;
                    }
                }
            },

            tooltip: {
                formatter: function () {
                    return '<b>' + this.x + '</b><br/>' +
                        this.series.name + ': ' + this.y + '<br/>' +
                        'Total: ' + this.point.stackTotal;
                }
            },

            // colors: [
            //     '#cc0000',
            //     '#258e25',
            //     '#cccc00',
            // ],

            plotOptions: {

                // bar: {colorByPoint: true},
                series: {
                    stacking: 'normal',
                    events: {
                        click: (event) => {

                            this.getDrillInnerDate(this.chartReq, event.point.category)
                        },

                    }

                }
            },

            legend: {
                enabled: false,
            },

            series: this.stack.innerChart,

            exporting:
            {

                buttons: {
                    contextButton: {
                        enabled: false
                    },
                    exportButton: {
                        text: 'Download',
                        menuItems: [
                            'downloadPNG',
                            'downloadJPEG',
                            'downloadPDF',
                            'downloadSVG'
                        ]
                    }
                }
            },
        };
    }
    BULead: string[];
    GroupLead: string[];

    eventNameBack: string;
    getDrillInnerDate(chartReq: ChartReq, eventName: string) {

        if (this.flag == 0) {
            this.stacked = true;
            this.lined = false;
            this.showLegand = true;
            this.show = true;
            this.previouspreviousFlag = this.flag;
            this.previouspriviousEventName = eventName;
            this.flag = 1;
            this.chartservice.getDrillInnerData(chartReq, eventName).subscribe((data) => {
                this.stack = data;
                this.getChartLoaded();
                this.chartservice.lineChartload = this.optionsForLine;
                this.chartservice.dashboardload = this.options;

            });
        } else if (this.flag == 1) {
            this.stacked = true;
            this.lined = false;
            this.showLegand = true;
            this.previousFlag = this.flag;
            this.priviousEventName = eventName;
            this.flag = 2;
            this.chartservice.getDrillMemberInnerData(chartReq, eventName).subscribe((data) => {
                this.stack = data;
                this.getChartLoaded();
                this.chartservice.lineChartload = this.optionsForLine;
                this.chartservice.dashboardload = this.options;
            });
        } else if (this.flag == 2) {
            this.stacked = false;
            this.lined = true;
            this.showLegand = false;
            this.flag = 3;
            this.chartservice.getDrillLineInnerData(chartReq, eventName).subscribe((data) => {
                this.stack = data;
                this.getLineChartLoaded();
                this.chartservice.lineChartload = this.optionsForLine;
                this.chartservice.dashboardload = this.options;

            });
        }
        else {
            this.getChartLoaded()
            this.chartservice.lineChartload = this.optionsForLine;
            this.chartservice.dashboardload = this.options;
        };
    }

    getAllBULeads() {
        this.chartservice.getAllBULeads().subscribe((data) => {
            this.BULead = data;
        });
    }

    getGroupLeadsByBULead(buLead: string) {
        this.chartservice.getGroupLeadsByBULead(buLead).subscribe((data) => {
            this.GroupLead = data;
        }
        );
    }
    back() {
        if (this.flag == 1) {
            this.show = false
            this.test();

        } else if (this.flag == 2) {
            this.flag = this.previouspreviousFlag;
            this.getDrillInnerDate(this.chartReq, this.previouspriviousEventName);

        } else {
            this.flag = this.previousFlag;
            this.getDrillInnerDate(this.chartReq, this.priviousEventName);

        }
    }



    getLineChartLoaded() {

        this.optionsForLine = {
            chart: {
                "width": 600,
                "height": 500
            },
            title: {
                text: 'CGI EWI , Grouped by Month'
            },
            xAxis: {
                categories: this.stack.dynamicXaxi
            },
            credits:
            {
                enabled: false,
            },
            yAxis: {
                title: {
                    text: 'Number of Members'
                }
            },
            plotOptions: {
                line: {
                    dataLabels: {
                        enabled: true
                    },
                    enableMouseTracking: false
                }
            },
            series: this.stack.innerChart
        };
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
