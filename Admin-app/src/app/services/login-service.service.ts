import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Router } from '@angular/router';

@Injectable()
export class LoginServiceService {

  constructor(private http: HttpClient,private router: Router) { }

  public login (admin){
    this.http.post('/login',admin).subscribe(
      data => { this.router.navigate(['workspace']);},
      error => { alert('Wrong username or password')}
    );
  }
}
