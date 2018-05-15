import { Component, OnInit } from '@angular/core';
import { CatalogServiceService } from '../services/catalog-service.service';

@Component({
  selector: 'app-catalog-editing',
  templateUrl: './catalog-editing.component.html',
  styleUrls: ['./catalog-editing.component.css']
})
export class CatalogEditingComponent implements OnInit {
  private tipovi;
  private kategorije;
  private dodatneUsluge;
  public editovanje = false;
  public brisanje = false;
  public idtext = "";
  public nametext = "";
  public opistext = "";
  public editInProgress = false;
  public tipr = false;
  public katr = false;
  public uslr = false;
  public mode = "New";

  constructor(private catalog_service: CatalogServiceService) { }

  ngOnInit() {
    this.tipovi = this.catalog_service.getTipove();
    this.kategorije = this.catalog_service.getKategorije();
    this.dodatneUsluge = this.catalog_service.getDodatneUsluge();
  }

  setEditovanje(){
    this.editovanje = !this.editovanje;
  }

  setBrisanje(){
    this.brisanje = !this.brisanje;
  }

  obrisiTip(value){
    console.log(value);
  }

  obrisiKategoriju(value){

  }

  obrisiUslugu(value){

  }

  test(r1,r2,r3,id,ime,opis){
    console.log("ID: ",id);
    console.log(r1);
    this.idtext = "";
    this.nametext = "";
    this.opistext = "";
    if(this.editInProgress == true){
      this.odustani();
    }
  }

  setForEditT(obj){
    this.editInProgress = true;
    this.mode = "Edit";
    this.tipr = true;
    this.katr = false;
    this.uslr = false;
    this.idtext = obj.idTipa;
    this.nametext = obj.nazivTipa;
    this.opistext = obj.opisTipa;
  }

  setForEditK(obj){
    this.editInProgress = true;
    this.mode = "Edit";
    this.tipr = false;
    this.katr = true;
    this.uslr = false;
    this.idtext = obj.idKategorije;
    this.nametext = obj.nazivKategorije;
    this.opistext = obj.opisKategorije;
  }

  setForEditE(obj){
    this.editInProgress = true;
    this.mode = "Edit";
    this.tipr = false;
    this.katr = false;
    this.uslr = true;
    this.idtext = obj.idUsluge;
    this.nametext = obj.nazivUsluge;
    this.opistext = obj.opisUsluge;
  }

  checkedT(){
    return this.tipr;
  }

  checkedK(){
    return this.katr;
  }

  checkedE(){
    return this.uslr;
  }

  odustani(){
    this.editInProgress = false;
    this.mode = "New";
    this.idtext = "";
    this.nametext = "";
    this.opistext = "";
    this.tipr = false;
    this.katr = false;
    this.uslr = false;
  }

}
