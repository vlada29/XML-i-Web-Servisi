import { Component, EventEmitter, OnInit, Output  } from '@angular/core';
import { LoginServiceService } from '../services/login-service.service';
import { ActivatedRoute, Router } from '@angular/router';
import 'rxjs/add/operator/filter'
import { HomeService } from '../services/home.service';
import { SearchDTO } from '../model/searchDTO';
import { AdvancedSearchDTO } from '../model/advancedsearchDTO';




@Component({
  selector: 'app-home',
  templateUrl: './home.component.html',
  styleUrls: ['./home.component.css']
})
export class HomeComponent implements OnInit {

  private loggedUser: any;
  private loggedUserId: any;

  private place: any = "";
  private from: any = 0;
  private to: any = 0;
  private numberPerson: any = "";

  private searchResults: any;

  private enableButtonLogout: boolean;
  private enableButtonLogin: boolean = true;

  @Output() onKatChanged = new EventEmitter<any>();
  @Output() onTipChanged = new EventEmitter<any>();

  private idKat;
  private idTip;

  private categories: any[];
  private types: any[];

  private parking: boolean;
  private wifi: boolean;
  private dorucak: boolean;
  private polupansion: boolean;
  private pansion: boolean;
  private tv: boolean;
  private kuhinja: boolean;
  private kupatilo: boolean;

  private enableError: boolean = false;



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

    this.homeService.getCategories().subscribe(data =>
    this.categories = data);

    this.homeService.getTypes().subscribe(data =>
    this.types = data);
  }

  search(){
    console.log('Parametri za pretragu'+this.place+', '+this.from+', '+
    this.to+', '+this.numberPerson);
    // to do: vallidacija na frontu
    //-za datume
    if (this.place!="" && this.from!=0
    && this.to!=0 && this.numberPerson!=""){
      let s = new SearchDTO(this.place, this.from, this.to, this.numberPerson);
      this.enableError = false;
      console.log(this.parking);
      if ((!this.parking) && (!this.wifi) && (!this.dorucak)
      && (!this.pansion) && (!this.polupansion) && (!this.tv)
      && (!this.kuhinja) && (!this.kupatilo) && (!this.idKat) && (!this.idTip)){
        console.log(this.parking);
        this.homeService.search(s).subscribe(data =>
            {this.searchResults = data;
              console.log(this.searchResults)
            });
 
          

      }else{

        let s = new AdvancedSearchDTO(this.place, this.from, this.to, this.numberPerson,
        this.parking, this.wifi, this.dorucak, this.polupansion, this.pansion, this.tv,
        this.kuhinja, this.kupatilo, this.categories[this.idKat].hjid, 
        this.types[this.idTip].hjid);
        this.homeService.searchAdvanced(s).subscribe(data =>
          {this.searchResults = data;
            console.log(this.searchResults)
          });


      }

      
    
     

    }else{
        this.enableError = true;

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

  onChangeKategorija(katValue) {
    this.onKatChanged.emit(katValue);
    this.idKat = katValue;
    console.log(this.idKat);

    
  }

  onChangeType(tipValue) {
    this.onTipChanged.emit(tipValue);
    this.idTip = tipValue;
    console.log(this.idTip);
    
  }

  search2(){
    console.log(this.parking);
    console.log(this.wifi);
    console.log(this.dorucak);
    console.log(this.polupansion);
    console.log(this.pansion);
    console.log(this.tv);
    console.log(this.kuhinja);
    console.log(this.kupatilo);
    
  }



}
