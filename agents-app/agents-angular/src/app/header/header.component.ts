import { Component, OnInit } from '@angular/core';
import { LoginService } from '../services/login.service';


@Component({
  selector: 'app-header',
  templateUrl: './header.component.html',
  styleUrls: ['./header.component.css']
})
export class HeaderComponent implements OnInit {

    public loggedIn = false;
     public username:string;
     constructor(private login_service: LoginService) {


     }

  ngOnInit() {
  }
  isLoggedIn() {
          return this.login_service.getLoggedIn();
      }

      getUsername(){
          return this.login_service.getUsername();
      }
}
