import { Component, EventEmitter, OnInit, Output  } from '@angular/core';
import { LoginServiceService } from '../services/login-service.service';
import { ActivatedRoute, Router } from '@angular/router';
import 'rxjs/add/operator/filter'
import { HomeService } from '../services/home.service';
import { SearchDTO } from '../model/searchDTO';
import { AdvancedSearchDTO } from '../model/advancedsearchDTO';
import { dodatnaDTO } from '../model/dodatnaDTO';




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

  private enableError: boolean = false;
  private sysdate = new Date().toJSON().slice(0,10);
  private enableErrorDate: boolean = false;

  private dodatneUsluge: any[];
  private doddto: Array<dodatnaDTO>;



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

    this.homeService.getDodatne().subscribe(data =>{
      this.dodatneUsluge = data;
      console.log(this.dodatneUsluge.length);
      for (let i = 0; i<this.dodatneUsluge.length; i++){
        this.dodatneUsluge[i] = 
          {
            "nazivUsluge": this.dodatneUsluge[i].nazivUsluge,
            "checked": false,
        };       
      }  
    });

    }

  search(){
    console.log('Parametri za pretragu'+this.place+', '+this.from+', '+
    this.to+', '+this.numberPerson);
    // to do: vallidacija na frontu
    //-za datume

    if (this.from<this.sysdate || this.to<this.sysdate ){
      this.enableError= false;
      this.enableErrorDate = true;
    }else{
      if (this.place!="" && this.from!=0
      && this.to!=0 && this.numberPerson!=""){
      let s = new SearchDTO(this.place, this.from, this.to, this.numberPerson);
      this.enableError = false;
      this.enableErrorDate = false;
      let varlength = this.dodatneUsluge.length;
      let brojac = 0;

      for (let i=0; i<varlength; i++){
 
        if (!this.dodatneUsluge[i].checked){
   
          brojac = brojac + 1;
        }
      }
      console.log(brojac);
      if (brojac==varlength && this.idKat==-1 && this.idTip==-1){
        console.log("regular search");

        
        this.homeService.search(s).subscribe(data =>
            {this.searchResults = data;
              console.log(this.searchResults)
            });
 
          

      }else{
        console.log("advanced search");
        this.doddto = [];
        for (let i = 0; i<this.dodatneUsluge.length; i++){
  
          this.doddto.push(new dodatnaDTO(this.dodatneUsluge[i].nazivUsluge, this.dodatneUsluge[i].checked));
        }
        let s;
        if (!this.idKat && !this.idTip){
          s = new AdvancedSearchDTO(this.place, this.from, this.to, this.numberPerson,
            this.doddto, "-1", "-1");
          
        }
        else{
          if (!this.idKat){
            s = new AdvancedSearchDTO(this.place, this.from, this.to, this.numberPerson,
              this.doddto, "-1", this.idTip );

          }
          else if (!this.idTip){
            s = new AdvancedSearchDTO(this.place, this.from, this.to, this.numberPerson,
              this.dodatneUsluge,  this.idKat, "-1",);

          }
          else{
          
            s = new AdvancedSearchDTO(this.place, this.from, this.to, this.numberPerson,
              this.dodatneUsluge, this.idKat, this.idTip);

          }

        }

        console.log(this.doddto);
        console.log(s);
        this.homeService.searchAdvanced(s).subscribe(data =>
          {this.searchResults = data;
            console.log(this.searchResults)
          });


      }

      
    
     

    }else{
        this.enableErrorDate = false;
        this.enableError = true;

    }


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
    for (let i = 0; i<this.dodatneUsluge.length; i++){
      console.log(this.dodatneUsluge[i].checked);
 
    }
    console.log(this.idKat);
    console.log(this.idTip);
    console.log(this.categories);

    
  }



}
