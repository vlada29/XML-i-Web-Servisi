import {Component, Directive, Input, ViewChild, OnInit} from '@angular/core';
import { UnitService } from '../services/unit.service';
import {HttpClient} from "@angular/common/http";
import {LoginService} from '../services/login.service';
import {FormsModule} from '@angular/forms';

@Component({
  selector: 'app-reservations',
  templateUrl: './reservations.component.html',
  styleUrls: ['./reservations.component.css']
})
export class ReservationsComponent implements OnInit {

    constructor(private unit_service: UnitService,
                private login_service: LoginService,
                private http: HttpClient){}
  ngOnInit(){
      this.getResOfMyUnits();

  }
  res_of_my_units : any;
  getResOfMyUnits(){
      this.http.get('/getResOfMyUnits/daca').subscribe(data => {
      if(data != null){
        console.log('Res of my units: ',data);
        this.res_of_my_units = data as any[];


      }
    })
  }

  confirmArrival(idRez){
      this.http.get('/confirmArrival/'+'daca/'+idRez).subscribe(data => {
      if(data != null){
        console.log('After confirming, res are: ',data);
        this.res_of_my_units = data as any[];


      }
    })
  }

}
