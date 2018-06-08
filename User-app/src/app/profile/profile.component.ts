import { Component, OnInit } from '@angular/core';
import { LoginServiceService } from '../services/login-service.service';
import { Router } from '@angular/router';
import { ProfileService } from '../services/profile.service';


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

  private reservations: any[];
  private reservationsPast: any[];

  constructor(private loginService: LoginServiceService,
  private router: Router, 
  private profileService: ProfileService
) { }

  ngOnInit() {

    this.loggedUserId = localStorage.getItem('currentUserId');
    console.log(this.loggedUserId);

    if (this.loggedUserId!=null){
      this.enableButtonLogout = true;
      this.enableButtonLogin = false;
      this.profileService.getRes(this.loggedUserId).subscribe(data =>
      this.reservations = data
      );
      this.profileService.getPastRes(this.loggedUserId).subscribe(data =>
        this.reservationsPast = data
        );

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

  cancel(id: any){
    console.log(id);
    this.profileService.cancel(id, this.loggedUserId).subscribe(data =>
    this.reservations = data);
  }

}
