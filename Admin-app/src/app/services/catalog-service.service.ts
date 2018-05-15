import { Injectable } from '@angular/core';
import { ITipSmestaja } from '../interfaces/ITipSmestaja';
import { IKategorijaSmestaja } from '../interfaces/IKategorijaSmestaja';
import { IDodatneUsluge } from '../interfaces/IDodatneUsluge';

@Injectable()
export class CatalogServiceService {
  private tipovi = null;
  private kategorije = null;
  private dodatneUsluge = null;

  constructor() { }

  public getTipove(): ITipSmestaja[]{
    this.tipovi = [{
      idTipa: 1, nazivTipa: 'Tip1', opisTipa: 'Opis tipa 1'
    },{
      idTipa: 2, nazivTipa: 'Tip2', opisTipa: 'Opis tipa 2'
    }];

    return this.tipovi;
  }

  public getKategorije(): IKategorijaSmestaja[]{
    this.kategorije = [{
      idKategorije: 1, nazivKategorije: 'Kategorija1', opisKategorije: 'Opis kategorije 1'
    },{
      idKategorije: 2, nazivKategorije: 'Kategorija2', opisKategorije: 'Opis kategorije 2'
    }];

    return this.kategorije;
  }

  public getDodatneUsluge(): IDodatneUsluge[]{
    this.dodatneUsluge = [{
      idUsluge: 1, nazivUsluge: 'Usluga1', opisUsluge: 'Opis usluge 1'
    },{
      idUsluge: 2, nazivUsluge: 'Usluga2', opisUsluge: 'Opis usluge 2'
    }];

    return this.dodatneUsluge;
  }

}
