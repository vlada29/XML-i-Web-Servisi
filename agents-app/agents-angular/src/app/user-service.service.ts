import { Injectable } from '@angular/core';
import { IUser } from './IUser';
import { HttpClient, HttpHeaders } from '@angular/common/http';
import { Subject, BehaviorSubject } from 'rxjs';
import {Observable} from 'rxjs/Observable';
import { Router } from '@angular/router';
@Injectable()
export class UserServiceService {
  private user;
  private loggedIn;
  private ws;

  constructor(private router:Router) { }

  setLoggedIn(){
     this.loggedIn = true;
 }
 getUsername(){
     return this.user.username;
 }
 getLoggedIn(){
     return this.loggedIn;
 }

 logout(){
  this.loggedIn = false;
  }

  getUser(): Observable<IUser> {
    return this.user;
  }

  login(username, password){

  }

}
