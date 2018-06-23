import { Component, OnInit } from '@angular/core';
import { LoginServiceService } from '../services/login-service.service';
import { Router, ActivatedRoute } from '@angular/router';
import { ProfileService } from '../services/profile.service';
import { HomeService } from '../services/home.service';
import { RatingService } from '../services/rating.service';


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
  private reservationsRealiz: any[];

  private rating: number[] = [];
  private showCom: boolean[] = [];
  private content: any;

  constructor(private loginService: LoginServiceService,
  private router: Router, 
  private profileService: ProfileService,
  private homeService: HomeService, 
  private ratingService: RatingService
) { }

  ngOnInit() {

    this.loggedUserId = localStorage.getItem('currentUserId');
    console.log(this.loggedUserId);

    if (this.loggedUserId!=null){
      this.enableButtonLogout = true;
      this.enableButtonLogin = false;
      this.profileService.getRes(this.loggedUserId).subscribe(data =>
      {this.reservations = data;
        console.log(this.reservations);
      }
      );
      this.profileService.getPastRes(this.loggedUserId).subscribe(data =>
        { this.reservationsRealiz = data;
          for (let i = 0; i<data.length; i++){
            this.showCom[i] = false;
          } }
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

  more(id: any){
    console.log(id);
    this.router.navigate(['/reservation'],  
    { queryParams: { id: id}, 
    });
  }

 
  addRate(r: any, i: any){
    console.log(this.loggedUser);
    console.log(r.smestajnaJedinica);
    console.log(this.rating[i]);
    if (this.rating[i]>0 && this.rating[i]<6){
      this.ratingService.postRating(r.smestajnaJedinica.hjid, this.loggedUser, this.rating[i]);
    }else{
      console.log("greska");
    }
    this.router.navigate(['/profile']);
  }

  showComment(i: any){
    console.log(i);
    this.showCom[i] = true;
  }

  addComment(r: any){
    this.ratingService.postComment(r.smestajnaJedinica.hjid, this.loggedUser, this.content);
  }

}
