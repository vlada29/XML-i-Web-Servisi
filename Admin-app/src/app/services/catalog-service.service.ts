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
  
  public obrisiTip(tip){
      this.http.post('/obrisiTip',tip).subscribe(
              data => { this.getTipove(); }, 
              error => { alert("Greska"); } 
      )
  }
  
  public obrisiKategoriju(kategorija){
      this.http.post('/obrisiKategoriju',kategorija).subscribe(
              data => { this.getKategorije(); }, 
              error => { alert("Greska"); } 
      )
  }
  
  public obrisiUslugu(usluga){
      this.http.post('/obrisiUslugu',usluga).subscribe(
              data => { this.getDodatneUsluge(); }, 
              error => { alert("Greska"); } 
      )
  }
  
  public snimiTip(tip){
      this.http.post('/snimiTip',tip).subscribe(
              data => {},
              error => { alert("Greska");}
      )
  }
  
  public snimiKategoriju(kategorija){
      this.http.post('/snimiKategoriju',kategorija).subscribe(
              data => {},
              error => { alert("Greska");}
      )
  }
  
  public snimiUslugu(usluga){
      this.http.post('/snimiUslugu',usluga).subscribe(
              data => {},
              error => { alert("Greska");}
      )
  }

}
