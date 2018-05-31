import { Injectable } from '@angular/core';
import { Router } from '@angular/router';
import { HttpClient } from "@angular/common/http";

@Injectable()
export class UnitService {
  constructor(private router:Router,
              private http: HttpClient) { }

  createNewUnit(unit){

      console.log(unit);
      this.http.post('/createNewUnit', unit).subscribe(data => {
          if(data != null){
              console.log('Vracen unit: ');
              console.log(data);
          } else {
              alert('Error while creating new unit!');
          }
       })
  }
}
