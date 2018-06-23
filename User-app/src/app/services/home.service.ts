import { Injectable } from '@angular/core';
import { Router } from '@angular/router';
import { HttpClient, HttpParams, HttpHeaders, HttpErrorResponse } from '@angular/common/http';
import { Observable } from 'rxjs/Observable';
import { SearchDTO } from '../model/searchDTO';
import { AdvancedSearchDTO } from '../model/advancedsearchDTO';

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
    .map((data:Observable<any>) => data)
    .catch((err:HttpErrorResponse) =>
    {
        alert(err.status + " Search error.");
        return Observable.throw(err);
    }); 
    // return this.http
    //      .get("http://localhost:8080/search", { headers: headers })
    //      .map((data: [any]) => data)
    //      .concatMap((searchResults: any[]) => {
    //          const observables = searchResults.map(r => this.http.get("http://localhost:8080/getCenu", {
    //              params: new HttpParams().append('idSmJed',r.smestajnaJedinica), headers: headers 
    //          }));
         
    //          return Observable.forkJoin(observables, (...results) => 
    //            results.map((result, i) => {
    //              cena[i] = result;
    //              return searchResults[i]; 
    //            })
    //          )
    //        }).takeLast(1) 
    //      .catch((err: HttpErrorResponse) => {
 
    //          return Observable.throw(err);
    //      });
    
}

reserve(id: any, idUser: any, from: any, to: any): Observable<any>{

    let params = new HttpParams().append('id', id);
    params = params.append('idUser', idUser);
    params = params.append('od', from);
    params = params.append('do', to);

    let headers = new HttpHeaders({ 
        'Content-Type': 'application/json'
     });

    return this.http.get("http://localhost:8080/reserve", {headers:headers, params: params})
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

    return this.http.get("http://localhost:8080/getCategories", {headers:headers})
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

    return this.http.get("http://localhost:8080/getTypes", {headers:headers})
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
    .post("http://localhost:8080/searchAdvanced", json, {headers:headers})
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

    return this.http.get("http://localhost:8080/getReservation", {headers:headers, params: params})
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

    return this.http.get("http://localhost:8080/getDodatne", {headers:headers})
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
    .post("http://localhost:8080/sortCena", json, {headers:headers})
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
    .post("http://localhost:8080/sortKategorija", json, {headers:headers})
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
    .post("http://localhost:8080/sortOcena", json, {headers:headers})
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

    return this.http.get("http://localhost:8080/getCom", {headers:headers, params: params})
    .map((data:Observable<any>) => data)
    .catch((err:HttpErrorResponse) =>
    {

        return Observable.throw(err);
    });
}









}
