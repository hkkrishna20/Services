import { NgModule } from '@angular/core';
import { Routes, RouterModule } from '@angular/router';

// Import Containers
import { DefaultLayoutComponent } from './containers';
import {AuthGuardService}  from './authguard/auth-guard.service';
import { P404Component } from './views/error/404.component';
import { P500Component } from './views/error/500.component';
import { LoginComponent } from './views/login/login.component';																																			
import { RegisterComponent } from './views/register/register.component';
import{BillingdetailsComponent} from "./ewi/billingdetails/billingdetails.component";
import{BillchartComponent} from "./ewi/billingdetails/billchart/billchart.component";
import{EwiComponent} from "./ewi/ewi.component";
import{DashboardComponent} from "./ewi/dashboard/dashboard.component";
import{DashboardchartComponent} from "./ewi/dashboard/dashboardchart/dashboardchart.component";
import{SowcompComponent} from "./ewi/sowcomp/sowcomp.component";
import{SowchartComponent} from "./ewi/sowcomp/sowchart/sowchart.component";
import { DashboardviewComponent } from './views/dashboardview/dashboardview.component';
export const routes: Routes = [

  {
    path: 'billchart',
    component: BillchartComponent,
	 canActivate: [AuthGuardService],
    pathMatch: 'full',
  },
  {
    path: 'dashboardview',
    component: DashboardviewComponent,
	 canActivate: [AuthGuardService],
    pathMatch: 'full',
  },

  {
    path: 'billingdetails',
	 canActivate: [AuthGuardService],
    component: BillingdetailsComponent,
    pathMatch: 'full',
  },
  {
    path: 'sowchart',
	 canActivate: [AuthGuardService],
    component: SowchartComponent,
    pathMatch: 'full',
  },
  {
    path: 'sowdetails',
	 canActivate: [AuthGuardService],
    component: SowcompComponent,
    pathMatch: 'full',
  },
  {
    path: 'ewiComponent',
	 canActivate: [AuthGuardService],
    component: EwiComponent,
    pathMatch: 'full',
  },

  {
    path: 'ewidashboard',
	 canActivate: [AuthGuardService],
    component: DashboardComponent,
    pathMatch: 'full',
  },
  {
    path: 'ewidashboardchart',
	 canActivate: [AuthGuardService],
    component: DashboardchartComponent,
    pathMatch: 'full',
  },
  {
    path: '404',
    component: P404Component,
    data: {
      title: 'Page 404'
    }
  },
  {
    path: '500',
    component: P500Component,
    data: {
      title: 'Page 500'
    }
  },
  {
    path: 'login',
    component: LoginComponent,
    data: {
      title: 'Login Page'
    }
  },
  {
    path: 'register',
    component: RegisterComponent,
    data: {
      title: 'Register Page'
    }
  },
  { path: '', redirectTo: 'dashboardview', pathMatch: 'full'},
  { path: '**', redirectTo: 'dashboardview', pathMatch: 'full'},
  {path: 'login',component: LoginComponent, pathMatch: 'full'},
  {path: 'dashboard', component: DashboardComponent},
 
  {
    path: 'home',
    component: DefaultLayoutComponent,
    data: {
      title: 'Home'
    },
    children: [
      {
        path: 'base',
        loadChildren: './views/base/base.module#BaseModule'
      },
      {
        path: 'buttons',
        loadChildren: './views/buttons/buttons.module#ButtonsModule'
      },
      {
        path: 'charts',
        loadChildren: './views/chartjs/chartjs.module#ChartJSModule'
      },
      {
        path: 'dashboard',
        loadChildren: './views/dashboard/dashboard.module#DashboardModule'
      },
      {
        path: 'icons',
        loadChildren: './views/icons/icons.module#IconsModule'
      },
      {
        path: 'notifications',
        loadChildren: './views/notifications/notifications.module#NotificationsModule'
      },
      {
        path: 'theme',
        loadChildren: './views/theme/theme.module#ThemeModule'
      },
      {
        path: 'widgets',
        loadChildren: './views/widgets/widgets.module#WidgetsModule'
      }
    ]
  },
  // { path: '**', component: P404Component }
];
@NgModule({
  imports: [ RouterModule.forRoot(routes) ],
  exports: [ RouterModule ]
})
export class AppRoutingModule {}
