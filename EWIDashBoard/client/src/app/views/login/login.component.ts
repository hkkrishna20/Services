import { Injectable } from '@angular/core';
import { Component, OnInit } from '@angular/core';
import { FormGroup, FormControl, Validators, FormGroupDirective, NgForm } from '@angular/forms';
import { ErrorStateMatcher } from '@angular/material';
import { Router, ActivatedRoute } from '@angular/router';
import { HttpClient } from '@angular/common/http';
import { trigger, state, style, animate, transition, query, stagger } from '@angular/animations';
import { LoginService } from '../../service/login.service';
import { AuthenticationService } from '../../service/authentication.service';
import { UserService } from '../../service/user.service';

export class MyErrorStateMatcher implements ErrorStateMatcher {
  isErrorState(control: FormControl | null, form: FormGroupDirective | NgForm | null): boolean {
    const isSubmitted = form && form.submitted;
    return !!(control && control.invalid && (control.dirty || control.touched || isSubmitted));
  }
}

@Component({
  selector: 'app-dashboard',
  templateUrl: 'login.component.html',
  styles: ['.bodySignIn {    background-size: cover;    width: 100%;    margin: 0 auto;    position: fixed;    left: 0;    right: 0;bottom: 0;    top: 0;  }  p {    background: grey;    color: whitesmoke;    border: 1px solid grey;    border-style: solid;    height: 25px;    width: 100%;    text-align: center;    border-radius: 10px;    border-color: whitesmoke;    outline: none;  }  .e2e-inner-html-bound {    height: 100%;  } .example-card {    width: 300px;    -webkit-box-shadow: 5px 5px 5px #F5002E;            box-shadow: 5px 5px 5px #F5002E;    background: #ffffff;    border: 1px solid grey;    border-radius: 10px;    outline: none;  }  .example-card .example-full-width {    width: 100%;  }  .example-card .mat-card-header {    -webkit-box-pack: center;        -ms-flex-pack: center;            justify-content: center;  }  @media (min-width: 768px) {    .example-card {      width: 500px;    }    .example-card .example-full-width {      width: 100%;   }    .example-card .mat-card-header {      -webkit-box-pack: center;          -ms-flex-pack: center;              justify-content: center;    }  }'],
  animations: [
    trigger('errorAnimations', [
      transition(':enter', [
        query('p', [
          style({ opacity: 0, transform: 'translateY(-100px)' }),
          stagger(-30, [
            animate('500ms cubic-bezier(0.35, 0, 0.25, 1)', style({ opacity: 1, transform: 'none' }))
          ])
        ])
      ])
    ]),
    trigger('filterAnimation', [
      transition(':enter, * => 0, * => -1', []),
      transition(':increment', [
        query(':enter', [
          style({ opacity: 0, width: '0px' }),
          stagger(50, [
            animate('300ms ease-out', style({ opacity: 1, width: '*' })),
          ]),
        ], { optional: true })
      ]),
      transition(':decrement', [
        query(':leave', [
          stagger(50, [
            animate('300ms ease-out', style({ opacity: 0, width: '0px' })),
          ]),
        ])
      ]),
    ]),
  ]
})

@Injectable()

export class LoginComponent implements OnInit {

  matCardTitle = 'EWI Dashboard';
  public credential = { 'username': '', 'password': '' };
  public loggedIn = false;
  htmlSnippet = '<i class="fa fa-warning"></i><b> Bad Username OR Password</b>';
  errorMessage: boolean;
  loading = false;
  error: string = '';
  redirectUrl: string;
returnUrl:string='';

  loginForm = new FormGroup({
    username: new FormControl('', [
      Validators.required,
      Validators.email,
    ]),
    password: new FormControl('', [
      Validators.required
    ]),
  });

  ngOnInit() {
    // localStorage.removeItem('xAuthToken');
    // this.userService.logout();
  }
  constructor(private http: HttpClient,
    private router: Router,
    private authenticationService: AuthenticationService,
    private userService: UserService,
    private activatedRoute: ActivatedRoute) {
    this.redirectUrl = this.activatedRoute.snapshot.queryParams['redirectTo'];
	 this.returnUrl = this.activatedRoute.snapshot.queryParams['return'] || '/';
    alert(this.redirectUrl);
  }

  onSubmit() {
    debugger;
    this.credential.username = this.loginForm.get("username").value;
    this.credential.password = this.loginForm.get("password").value;

    // this.loginService.sendCredential(this.credential.username, this.credential.password).subscribe(
    //   res => {
    //     console.log(res);
    //     localStorage.setItem("xAuthToken", res.json().token);
    //     this.loggedIn = true;
    //     this.router.navigate(['/home']);
    //   },
    //   error => {
    //     console.log(error);
    //     this.errorMessage = true;
    //   }
    // );

    this.loading = true;

    this.authenticationService.login(this.credential.username, this.credential.password).subscribe(
      result => {
        alert(result);
        result = result.json();
        this.authenticationService.saveToken(result);

        if (null != result.access_token) {
          alert("Inside if");
          this.loading = true;
          this.userService.login(result.access_token);
          this.navigateAfterSuccess();
        } else {
          this.error = 'Username or password is incorrect';
        }
      },
      error => {
        this.error = 'Username or password is incorrect';
        this.loading = false;
      }
    );
  }
  private navigateAfterSuccess() {
    alert("navigateAfterSuccess");
	
    if (this.redirectUrl) {
this.router.navigate([this.returnUrl]);
	
    } else {
this.router.navigateByUrl(["/"]);

  }
  }
}
