import { Injectable } from '@angular/core';
import { Router } from '@angular/router';
import { HttpClient, HttpParams, HttpHeaders, HttpErrorResponse } from '@angular/common/http';
import { Observable } from 'rxjs/Observable';
import { SearchDTO } from '../model/searchDTO';
import { AdvancedSearchDTO } from '../model/advancedsearchDTO';
import 'rxjs/add/operator/map';
import 'rxjs/add/operator/catch';
import 'rxjs/add/operator/toPromise';
import 'rxjs/add/operator/concatMap';
import 'rxjs/add/observable/throw';
import 'rxjs/add/observable/forkJoin';
import 'rxjs/add/operator/takeLast';

@Injectable()
export class HomeService {

    private url: any = "http://ac42ab69.ngrok.io"

  constructor(private http:HttpClient, private router: Router) { }

search(searchDTO : SearchDTO) : Observable<any> {

    let headers = new HttpHeaders({ 
        'Content-Type': 'application/json'
     });

    let json = JSON.parse(JSON.stringify(searchDTO));
    console.log(json);
    return this.http
    .post(this.url+"/search", json, {headers:headers})
    .map((data:Observable<any>) => data)
    .catch((err:HttpErrorResponse) =>
    {
        alert(err.status + " Search error.");
        return Observable.throw(err);
    }); 

    
}

reserve(id: any, idUser: any, from: any, to: any): Observable<any>{

    let params = new HttpParams().append('id', id);
    params = params.append('idUser', idUser);
    params = params.append('od', from);
    params = params.append('do', to);

    let headers = new HttpHeaders({ 
        'Content-Type': 'application/json'
     });

    return this.http.get(this.url+"/reserve", {headers:headers, params: params})
    .map((data:Observable<any>) => data)
    .catch((err:HttpErrorResponse) =>
    {
        alert(err.status + " Please log in or register to make a reservation.");
        this.router.navigateByUrl('login');
        return Observable.throw(err);
    });
}

getCategories(): Observable<any>{


    let headers = new HttpHeaders({ 
        'Content-Type': 'application/json'
     });

    return this.http.get(this.url+"/getCategories", {headers:headers})
    .map((data:Observable<any>) => data)
    .catch((err:HttpErrorResponse) =>
    {

        return Observable.throw(err);
    });
}

getTypes(): Observable<any>{


    let headers = new HttpHeaders({ 
        'Content-Type': 'application/json'
     });

    return this.http.get(this.url+"/getTypes", {headers:headers})
    .map((data:Observable<any>) => data)
    .catch((err:HttpErrorResponse) =>
    {

        return Observable.throw(err);
    });
}

searchAdvanced(advancedsearchDTO : AdvancedSearchDTO) : Observable<any> {

    let headers = new HttpHeaders({ 
        'Content-Type': 'application/json'
     });
    
    console.log(advancedsearchDTO);

    let json = JSON.parse(JSON.stringify(advancedsearchDTO));
    

    console.log(json);
    return this.http
    .post(this.url+"/searchAdvanced", json, {headers:headers})
    .map((data:Observable<any>) => data)
    .catch((err:HttpErrorResponse) =>
    {
        alert(err.status + " Search error.");
        return Observable.throw(err);
    }); 
    
}

getReservation(id: any): Observable<any>{

    let params = new HttpParams().append('id', id);
    let headers = new HttpHeaders({ 
        'Content-Type': 'application/json'
     });

    return this.http.get(this.url+"/getReservation", {headers:headers, params: params})
    .map((data:Observable<any>) => data)
    .catch((err:HttpErrorResponse) =>
    {

        return Observable.throw(err);
    });

    

}

getDodatne(): Observable<any>{


    let headers = new HttpHeaders({ 
        'Content-Type': 'application/json'
     });

    return this.http.get(this.url+"/getDodatne", {headers:headers})
    .map((data:Observable<any>) => data)
    .catch((err:HttpErrorResponse) =>
    {

        return Observable.throw(err);
    });
}

sortCena(searchList: any) : Observable<any> {

    let headers = new HttpHeaders({ 
        'Content-Type': 'application/json'
     });
    
    console.log(searchList);

    let json = JSON.parse(JSON.stringify(searchList));
    

    console.log(json);
    return this.http
    .post(this.url+"/sortCena", json, {headers:headers})
    .map((data:Observable<any>) => data)
    .catch((err:HttpErrorResponse) =>
    {
        alert(err.status + " Search error.");
        return Observable.throw(err);
    }); 
    
}

sortKat(searchList: any) : Observable<any> {

    let headers = new HttpHeaders({ 
        'Content-Type': 'application/json'
     });
    
    console.log(searchList);

    let json = JSON.parse(JSON.stringify(searchList));
    

    console.log(json);
    return this.http
    .post(this.url+"/sortKategorija", json, {headers:headers})
    .map((data:Observable<any>) => data)
    .catch((err:HttpErrorResponse) =>
    {
        alert(err.status + " Search error.");
        return Observable.throw(err);
    }); 
    
}

sortOcena(searchList: any) : Observable<any> {

    let headers = new HttpHeaders({ 
        'Content-Type': 'application/json'
     });
    
    console.log(searchList);

    let json = JSON.parse(JSON.stringify(searchList));
    

    console.log(json);
    return this.http
    .post(this.url+"/sortOcena", json, {headers:headers})
    .map((data:Observable<any>) => data)
    .catch((err:HttpErrorResponse) =>
    {
        alert(err.status + " Search error.");
        return Observable.throw(err);
    }); 
    
}

getCom(id: any): Observable<any>{

    let params = new HttpParams().append('id', id);
    let headers = new HttpHeaders({ 
        'Content-Type': 'application/json'
     });

    return this.http.get(this.url+"/getCom", {headers:headers, params: params})
    .map((data:Observable<any>) => data)
    .catch((err:HttpErrorResponse) =>
    {

        return Observable.throw(err);
    });
}









}
