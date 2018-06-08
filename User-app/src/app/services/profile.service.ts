import { Injectable } from '@angular/core';
import { HttpParams, HttpHeaders, HttpErrorResponse, HttpClient } from '@angular/common/http';
import { Observable } from 'rxjs/Observable';
import { Router } from '@angular/router';

import 'rxjs/add/operator/map';
import 'rxjs/add/operator/catch';
import 'rxjs/add/operator/toPromise';
import 'rxjs/add/operator/concatMap';
import 'rxjs/add/observable/throw';
import 'rxjs/add/observable/forkJoin';
import 'rxjs/add/operator/takeLast';

@Injectable()
export class ProfileService {

  constructor(private http:HttpClient, private router: Router) { }

  getRes(idUser: any){

    let params = new HttpParams().append('idUser', idUser);


    let headers = new HttpHeaders({ 
        'Content-Type': 'application/json'
     });


    return this.http.get("http://localhost:8080/getReservations", {headers:headers, params: params})
    .map((data:Observable<any>) => data)
    .catch((err:HttpErrorResponse) =>
    {

        return Observable.throw(err);
    });
    // return this.http
    //      .get("http://localhost:8080/getReservations", { params: params, headers: headers })
    //      .map((data: [any]) => data)
    //      .concatMap((reservations: any[]) => {
    //          const observables = reservations.map(r => this.http.get("http://localhost:8080/getResInfo", {
    //              params: new HttpParams().append('idSmJed',r.smestajnaJedinica), headers: headers 
    //          }));
         
    //          return Observable.forkJoin(observables, (...results) => 
    //            results.map((result, i) => {
    //              reservations[i].smestajnaJedinica = result;
    //              return reservations[i]; 
    //            })
    //          )
    //        }).takeLast(1) 
    //      .catch((err: HttpErrorResponse) => {
 
    //          return Observable.throw(err);
    //      });

  }

  getPastRes(idUser: any){

    let params = new HttpParams().append('idUser', idUser);


    let headers = new HttpHeaders({ 
        'Content-Type': 'application/json'
     });

     
    return this.http.get("http://localhost:8080/getPastReservations", {headers:headers, params: params})
    .map((data:Observable<any>) => data)
    .catch((err:HttpErrorResponse) =>
    {

        return Observable.throw(err);
    });

  }

  cancel(id: any, idUser: any){

    let params = new HttpParams().append('id', id);
    params = params.append('idUser', idUser);


    let headers = new HttpHeaders({ 
        'Content-Type': 'application/json'
     });


    return this.http.get("http://localhost:8080/cancel", {headers:headers, params: params})
    .map((data:Observable<any>) => data)
    .catch((err:HttpErrorResponse) =>
    {

        return Observable.throw(err);
    });
  }

}
