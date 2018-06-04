import { Injectable } from '@angular/core';
import { Observable } from 'rxjs/Observable';
import { HttpHeaders, HttpClient, HttpErrorResponse, HttpParams } from '@angular/common/http';
import { Router } from '@angular/router';
import 'rxjs/add/operator/map';
import 'rxjs/add/operator/catch';
import 'rxjs/add/operator/toPromise';
import 'rxjs/Observable';
import 'rxjs/add/observable/throw';


@Injectable()
export class LoginServiceService {

  constructor(private http:HttpClient, private router: Router) { }

  // for login and registration component

  submitRegistration(user : any) : Observable<any> {

    let headers = new HttpHeaders({ 
        'Content-Type': 'application/json'
     });

    let json = JSON.parse(JSON.stringify(user));
    console.log(json);
    return this.http
    .post("http://localhost:8080/registrationMessage", json, {headers:headers})
    .catch((err:HttpErrorResponse) =>
    {
        alert(err.status + " Registration failed.");
        return Observable.throw(err);
    }); 
    
}

submitLogin(user: any) : Observable<any>{

    let headers = new HttpHeaders({ 
        'Content-Type': 'application/json'
     });

    let json = JSON.parse(JSON.stringify(user));
    console.log(json);
    return this.http
    .post("http://localhost:8080/userLogin", json, {headers:headers})
    .map((data:Observable<any>) => data)
    .catch((err:HttpErrorResponse) =>
    {
        alert(err.status + " Login failed.");
        return Observable.throw(err);
    }); 

}


getLoggedUser(): Observable<any>{


    let headers = new HttpHeaders({ 
        'Content-Type': 'application/json'
     });

    return this.http.get("http://localhost:8080/getLoggedUser", {headers:headers})
    .map((data:Observable<any>) => data)
    .catch((err:HttpErrorResponse) =>
    {
        alert(err.status + "Unauthorized!");
        this.router.navigateByUrl('login');
        return Observable.throw(err);
    });
}

getLoggedUserById(id: any): Observable<any>{

    let params = new HttpParams().append('id', id);
    let headers = new HttpHeaders({ 
        'Content-Type': 'application/json'
     });

    return this.http.get("http://localhost:8080/getLoggedUserById", {headers:headers, params: params})
    .map((data:Observable<any>) => data)
    .catch((err:HttpErrorResponse) =>
    {

        return Observable.throw(err);
    });
}


  

}
