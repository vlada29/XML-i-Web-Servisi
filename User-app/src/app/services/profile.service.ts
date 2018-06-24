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

    private url: any = "http://ac42ab69.ngrok.io"

  constructor(private http:HttpClient, private router: Router) { }

  getRes(idUser: any){

    let params = new HttpParams().append('idUser', idUser);


    let headers = new HttpHeaders({ 
        'Content-Type': 'application/json'
     });

    return this.http
         .get(this.url+"/getReservations", { params: params, headers: headers })
         .map((data: [any]) => data)
         .concatMap((reservations: any[]) => {
             const observables = reservations.map(r => this.http.get(this.url+"/getOcena", {
                 params: new HttpParams().append('id',r.smestajnaJedinica.hjid), headers: headers 
             }));
         
             return Observable.forkJoin(observables, (...results) => 
               results.map((result, i) => {
                 reservations[i].trenutnaOcena = result;
                 return reservations[i]; 
               })
             )
           }).takeLast(1) 
         .catch((err: HttpErrorResponse) => {
 
             return Observable.throw(err);
         });

  }

  getPastRes(idUser: any){

    let params = new HttpParams().append('idUser', idUser);


    let headers = new HttpHeaders({ 
        'Content-Type': 'application/json'
     });


    return this.http
         .get(this.url+"/getPastReservations", { params: params, headers: headers })
         .map((data: [any]) => data)
         .concatMap((reservations: any[]) => {
             const observables = reservations.map(r => this.http.get(this.url+"/getOcena", {
                 params: new HttpParams().append('id',r.smestajnaJedinica.hjid), headers: headers 
             }));
         
             return Observable.forkJoin(observables, (...results) => 
               results.map((result, i) => {
                 reservations[i].trenutnaOcena = result;
                 console.log(reservations[i].trenutnaOcena);
                 return reservations[i]; 
               })
             )
           }).takeLast(1) 
         .catch((err: HttpErrorResponse) => {
 
             return Observable.throw(err);
         });

  }

  cancel(id: any, idUser: any){

    let params = new HttpParams().append('id', id);
    params = params.append('idUser', idUser);


    let headers = new HttpHeaders({ 
        'Content-Type': 'application/json'
     });


    return this.http.get(this.url+"/cancel", {headers:headers, params: params})
    .map((data:Observable<any>) => data)
    .catch((err:HttpErrorResponse) =>
    {

        return Observable.throw(err);
    });
  }

  sendMessage(message: any) : Observable<any> {

    let headers = new HttpHeaders({ 
        'Content-Type': 'application/json'
     });

    let json = JSON.parse(JSON.stringify(message));
    console.log(json);
    return this.http
    .post(this.url+"/sendMessage", json, {headers:headers})
    .map((data:Observable<any>) => data)
    .catch((err:HttpErrorResponse) =>
    {
        alert(err.status + " Message could not be sent.");
        return Observable.throw(err);
    }); 
    
}

getSentMessages(idUser: any){

    let params = new HttpParams().append('idUser', idUser);


    let headers = new HttpHeaders({ 
        'Content-Type': 'application/json'
     });

     
    return this.http.get(this.url+"/getSentMessages", {headers:headers, params: params})
    .map((data:Observable<any>) => data)
    .catch((err:HttpErrorResponse) =>
    {

        return Observable.throw(err);
    });

  }

  getReceivedMessages(idUser: any){

    let params = new HttpParams().append('idUser', idUser);


    let headers = new HttpHeaders({ 
        'Content-Type': 'application/json'
     });

     
    return this.http.get(this.url+"/getReceivedMessages", {headers:headers, params: params})
    .map((data:Observable<any>) => data)
    .catch((err:HttpErrorResponse) =>
    {

        return Observable.throw(err);
    });

  }

  getMessage(id: any){

    let params = new HttpParams().append('id', id);


    let headers = new HttpHeaders({ 
        'Content-Type': 'application/json'
     });


    return this.http.get(this.url+"/getMessage", {headers:headers, params: params})
    .map((data:Observable<any>) => data)
    .catch((err:HttpErrorResponse) =>
    {

        return Observable.throw(err);
    });
  }

}
