import { Injectable } from '@angular/core';
import { Router } from '@angular/router';
import { HttpClient, HttpParams, HttpHeaders, HttpErrorResponse } from '@angular/common/http';
import { Observable } from 'rxjs/Observable';
import { SearchDTO } from '../model/searchDTO';

@Injectable()
export class HomeService {

  constructor(private http:HttpClient, private router: Router) { }

  search(searchDTO : SearchDTO) : Observable<any> {

    let headers = new HttpHeaders({ 
        'Content-Type': 'application/json'
     });

    let json = JSON.parse(JSON.stringify(searchDTO));
    console.log(json);
    return this.http
    .post("http://localhost:8080/search", json, {headers:headers})
    .catch((err:HttpErrorResponse) =>
    {
        alert(err.status + " Search error.");
        return Observable.throw(err);
    }); 
    
}



}
