import { Component, OnInit } from '@angular/core';
import { LoginServiceService } from '../services/login-service.service';
import { Router } from '@angular/router';

@Component({
  selector: 'app-profile',
  templateUrl: './profile.component.html',
  styleUrls: ['./profile.component.css']
})
export class ProfileComponent implements OnInit {

  private loggedUser: any;
  private loggedUserId: any;

  private enableButtonLogout: boolean;
  private enableButtonLogin: boolean = true;

  constructor(private loginService: LoginServiceService,
  private router: Router) { }

  ngOnInit() {

    this.loggedUserId = localStorage.getItem('currentUserId');
    console.log(this.loggedUserId);

    if (this.loggedUserId!=null){
      this.enableButtonLogout = true;
      this.enableButtonLogin = false;
    }

    this.loginService.getLoggedUserById(this.loggedUserId).subscribe(data =>
      this.loggedUser = data);
  }

  logout(){
    localStorage.removeItem('currentUserId');
    this.router.navigate(['/login']);
    this.enableButtonLogin = true;
    this.enableButtonLogout = false;

  }

}
