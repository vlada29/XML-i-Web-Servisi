import { Component, OnInit, ViewChild } from '@angular/core';
import { LoginServiceService } from '../services/login-service.service';
import { IAdmin } from '../interfaces/IAdmin';

@Component({
  selector: 'app-login',
  templateUrl: './login.component.html',
  styleUrls: ['./login.component.css']
})
export class LoginComponent implements OnInit {
  @ViewChild('username') username:any;
  @ViewChild('password') password:any;

  constructor(private login_service: LoginServiceService) { }

  ngOnInit() {
  }

  login(e){
    e.preventDefault();
    console.log(this.username.nativeElement.value, this.password.nativeElement.value);
    var admin: IAdmin = {
      username:this.username.nativeElement.value,
      password:this.password.nativeElement.value,
      firstName: '',
      lastName: '',
      hjid: 0
    }
    this.login_service.login(admin);
  }

}
