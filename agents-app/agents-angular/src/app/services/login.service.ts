import { Injectable } from '@angular/core';
import { Router } from '@angular/router';
import {Observable} from 'rxjs/Observable';
import { IUser } from '../interfaces/Iuser';

import { HttpClient, HttpHeaders } from '@angular/common/http';
@Injectable()
export class LoginService {
    private user;
    private loggedIn;
    private ws;

    constructor(private router:Router,
                private http: HttpClient) { }

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
    setUser(data) {

    this.user = data;
    this.loggedIn = true;

    console.log('user set', this.user.username);
  }
    login(username, password){
        var user = {
         username: username,
         password: password
        }

        this.http.post('/login', user).subscribe(data => {
            if(data != null){
                this.setUser(data);
                this.router.navigate(['userpage']);
            } else {
                alert('Wrong username or password');
            }
         })
    }
}
