import { Injectable } from '@angular/core';
import { Router } from '@angular/router';
import {Observable} from 'rxjs/Observable';
import { IUser } from '../interfaces/IUser';

import { HttpClient, HttpHeaders } from '@angular/common/http';
@Injectable()
export class LoginService {
    public user;
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
    this.user = null;
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
        this.router.navigate(['sync']);
        this.http.post('/login', user).subscribe(data => {

            if(data != null){
                this.setUser(data);
                this.router.navigate(['workspace']);
            } else {
                alert('Wrong username or password');
                this.router.navigate(['login']);
            }
         })
    }
}
