import { Injectable } from '@angular/core';
import { HttpClient, HttpParams, HttpHeaders, HttpErrorResponse } from '@angular/common/http';
import { Observable } from 'rxjs/Observable';


@Injectable()
export class RatingService {

  private url: any ="http://ac42ab69.ngrok.io";

  constructor(private http: HttpClient) { }

  public postComment(smestajnaJedinica,user,sadrzaj){
      var endpoint = 'https://us-central1-rating-system-ca802.cloudfunctions.net/dodajKomentar';

      const comment = {
        hjid: 0,  
        smestajnaJedinica: smestajnaJedinica, 
        user: user, 
        sadrzaj: sadrzaj,
        odobren: false
      };

      this.http.post(endpoint,comment).subscribe(data => {});
  }
  
  public postRating(smestajnaJedinica,user,ocena){
      var endpoint2 = 'https://us-central1-rating-system-ca802.cloudfunctions.net/addRating';

      const rating = {
        hjid: 0,
        smestajnaJedinica: smestajnaJedinica,
        user: user,
        ocena: parseInt(ocena)
      };
      console.log(rating);

      this.http.post(endpoint2,rating).subscribe(data => {});
  }

  getOcena(id: any): Observable<any>{

    let params = new HttpParams().append('id', id);

    let headers = new HttpHeaders({ 
        'Content-Type': 'application/json'
     });

    return this.http.get(this.url+"/getOcena", {headers:headers, params: params})
    .map((data:Observable<any>) => data)
    .catch((err:HttpErrorResponse) =>
    {
        return Observable.throw(err);
    });
}

greater(pretragaLista: any, broj: any) : Observable<any> {

  let params = new HttpParams().append('broj', broj);

  let headers = new HttpHeaders({ 
      'Content-Type': 'application/json'
   });

  
  let json = JSON.parse(JSON.stringify(pretragaLista));
  console.log(json);
  return this.http
  .post(this.url+"/greater", json, {headers:headers, params: params})
  .map((data:Observable<any>) => data)
  .catch((err:HttpErrorResponse) =>
  {
      alert(err.status + " Search error.");
      return Observable.throw(err);
  }); 

  
}

less(pretragaLista: any, broj: any) : Observable<any> {

  let params = new HttpParams().append('broj', broj);

  let headers = new HttpHeaders({ 
      'Content-Type': 'application/json'
   });

  
  let json = JSON.parse(JSON.stringify(pretragaLista));
  console.log(json);
  return this.http
  .post(this.url+"/less", json, {headers:headers, params: params})
  .map((data:Observable<any>) => data)
  .catch((err:HttpErrorResponse) =>
  {
      alert(err.status + " Search error.");
      return Observable.throw(err);
  }); 

  
}

equal(pretragaLista: any, broj: any) : Observable<any> {

  let params = new HttpParams().append('broj', broj);

  let headers = new HttpHeaders({ 
      'Content-Type': 'application/json'
   });

  
  let json = JSON.parse(JSON.stringify(pretragaLista));
  console.log(json);
  return this.http
  .post(this.url+"/equal", json, {headers:headers, params: params})
  .map((data:Observable<any>) => data)
  .catch((err:HttpErrorResponse) =>
  {
      alert(err.status + " Search error.");
      return Observable.throw(err);
  }); 

  
}







  
  
}
