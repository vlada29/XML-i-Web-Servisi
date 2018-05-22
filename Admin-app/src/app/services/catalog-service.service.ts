import { Injectable } from '@angular/core';
import { ITipSmestaja } from '../interfaces/ITipSmestaja';
import { IKategorijaSmestaja } from '../interfaces/IKategorijaSmestaja';
import { IDodatneUsluge } from '../interfaces/IDodatneUsluge';
import { HttpClient } from '@angular/common/http';
import { Observable } from "rxjs/Observable";

@Injectable()
export class CatalogServiceService {

  constructor(private http: HttpClient) { }

  public getTipove(): Observable<ITipSmestaja[]>{
    return this.http.get<ITipSmestaja[]>('/getTipovi');
  }

  public getKategorije(): Observable<IKategorijaSmestaja[]>{
    return this.http.get<IKategorijaSmestaja[]>('/getKategorije');
  }

  public getDodatneUsluge(): Observable<IDodatneUsluge[]>{
    return this.http.get<IDodatneUsluge[]>('/getDodatneUsluge');
  }
  
  public obrisiTip(tip): Observable<any>{
      return this.http.post('/obrisiTip',tip);
  }
  
  public obrisiKategoriju(kategorija): Observable<any>{
      return this.http.post('/obrisiKategoriju',kategorija);
  }
  
  public obrisiUslugu(usluga): Observable<any>{
      return this.http.post('/obrisiUslugu',usluga);
  }
  
  public snimiTip(tip): Observable<any>{
      return this.http.post('/snimiTip',tip);
  }
  
  public snimiKategoriju(kategorija): Observable<any>{
      return this.http.post('/snimiKategoriju',kategorija);
  }
  
  public snimiUslugu(usluga): Observable<any>{
      return this.http.post('/snimiUslugu',usluga);
  }

}
