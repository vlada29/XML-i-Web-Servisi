import { Component, EventEmitter, OnInit, Output  } from '@angular/core';
import { LoginServiceService } from '../services/login-service.service';
import { ActivatedRoute, Router } from '@angular/router';
import 'rxjs/add/operator/filter'
import { HomeService } from '../services/home.service';
import { SearchDTO } from '../model/searchDTO';




@Component({
  selector: 'app-home',
  templateUrl: './home.component.html',
  styleUrls: ['./home.component.css']
})
export class HomeComponent implements OnInit {

  private loggedUser: any;
  private loggedUserId: any;

  private place: any;
  private from: any;
  private to: any;
  private numberPerson: any;

  private searchResults: any;

  private enableButtonLogout: boolean;
  private enableButtonLogin: boolean = true;


  constructor(private loginService: LoginServiceService,
    private route: ActivatedRoute, 
    private homeService: HomeService, 
    private router: Router) {

     }

  ngOnInit() {

    // za security - ne radi
    // this.loginService.getLoggedUser().subscribe(data =>
    // this.loggedUser = data);

    this.loggedUserId = localStorage.getItem('currentUserId');
    console.log(this.loggedUserId);

    if (this.loggedUserId!=null){
      this.enableButtonLogout = true;
      this.enableButtonLogin = false;
    }

    this.loginService.getLoggedUserById(this.loggedUserId).subscribe(data =>
      this.loggedUser = data);
  }

  search(){
    console.log('Parametri za pretragu'+this.place+', '+this.from+', '+
    this.to+', '+this.numberPerson);
    // to do: vallidacija na frontu
    //-za datume
    if (this.place!="undefined" && this.from!="undefined"
    && this.to!="undefined" && this.numberPerson!="undefined"){
      let s = new SearchDTO(this.place, this.from, this.to, this.numberPerson);
     this.homeService.search(s).subscribe(data =>
    {this.searchResults = data;
      console.log(this.searchResults)
    });
    
     

    }

  }

  logout(){
    localStorage.removeItem('currentUserId');
    this.router.navigate(['/home']);
    this.enableButtonLogin = true;
    this.enableButtonLogout = false;

  }

  reserve(id: any, from: any, to: any){
    console.log(id);
    this.homeService.reserve(id, 
      localStorage.getItem('currentUserId'),
    from, to).subscribe(data => {
      this.router.navigate(['/profile'])

    });
  }



}
