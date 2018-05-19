import { Injectable } from '@angular/core';
import { IUser } from '../interfaces/IUser';
import { HttpClient } from '@angular/common/http';
import { IKomentar } from '../interfaces/IKomentar';
import { IOcena } from '../interfaces/IOcena';
import { Observable } from "rxjs/Observable";

@Injectable()
export class UserServiceService {

  private users = null;

  constructor(private http: HttpClient) { }

  public getUsers(): Observable<IUser[]>{
    return this.http.get<IUser[]>('/getAllUsers');
  }

  public removeUser(user){
    var endpoint = 'https://us-central1-rating-system-ca802.cloudfunctions.net/addComment';

    const data2 = {
      idKomentara: 0,  
      smestajnaJedinica: 1, 
      user: {
        ime: 'Vlada',prezime: 'Djurdjevic', username: 'Dovla29', password: 'Dovla29', active: true
      }, 
      sadrzaj: 'Ovaj smestaj je bio sjajan',
      odobren: false
    };

    this.http.post(endpoint,data2).subscribe(data => {});
    
//  this.http.post('/removeUser',user).subscribe(
//    data => {}, 
//    error => { alert("Greska"); } 
//  )

  }

  public activateUser(user){
    var endpoint3 = 'https://us-central1-rating-system-ca802.cloudfunctions.net/getRating';

    this.http.get<IOcena>(endpoint3).subscribe(data => console.log(data));
    
//  this.http.post('/activateUser',user).subscribe(
//    data => {}, 
//    error => { alert("Greska"); } 
//  )

  }

  public blockUser(user){
    var endpoint2 = 'https://us-central1-rating-system-ca802.cloudfunctions.net/addRating';

    const data3 = {
      idOcene: 1,
      smestajnaJedinica: 1,
      user: {
        ime: 'Vlada',prezime: 'Djurdjevic', username: 'Dovla29', password: 'Dovla29', active: true
      },
      ocena: 10
    }

    this.http.post(endpoint2,data3).subscribe(data => {});
    
//    this.http.post('/blockUser',user).subscribe(
//      data => {}, 
//      error => { alert("Greska"); } 
//    )

  }

}
