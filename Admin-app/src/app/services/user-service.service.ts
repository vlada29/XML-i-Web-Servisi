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
    this.http.post('/removeUser',user).subscribe(
      data => {}, 
      error => { alert("Greska"); } 
    )
  }

  public activateUser(user){
    this.http.post('/activateUser',user).subscribe(
      data => {}, 
      error => { alert("Greska"); } 
    )
  }

  public blockUser(user){
    this.http.post('/blockUser',user).subscribe(
      data => {}, 
      error => { alert("Greska"); } 
    )
  }

}
