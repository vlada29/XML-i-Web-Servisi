import { Component, OnInit,ViewChild } from '@angular/core';
import { Router } from '@angular/router';
import { HttpClient } from "@angular/common/http";
import { LoginService } from '../services/login.service';
@Component({
  selector: 'app-login',
  templateUrl: './login.component.html',
  styleUrls: ['./login.component.css']
})
export class LoginComponent implements OnInit {

    constructor(
        private login_service: LoginService) { }

  ngOnInit(){}
  @ViewChild('username') username:any;
  @ViewChild('password') password:any;

  login(){
      console.log(this.username.nativeElement.value, this.password.nativeElement.value);
      
      this.login_service.login(this.username.nativeElement.value, this.password.nativeElement.value);

      return false;
  }
}
