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
    this.catalog_service.getTipove().subscribe(data => this.tipovi = data);
    this.catalog_service.getKategorije().subscribe(data => this.kategorije = data);
    this.catalog_service.getDodatneUsluge().subscribe(data => this.dodatneUsluge = data);
  }

  setEditovanje(){
    this.editovanje = !this.editovanje;
  }

  setBrisanje(){
    this.brisanje = !this.brisanje;
  }

  obrisiTip(value){
    this.catalog_service.obrisiTip(value).subscribe(
            data => {this.catalog_service.getTipove().subscribe(data => this.tipovi = data);},
            error => {alert("Greska")});
  }

  obrisiKategoriju(value){
    this.catalog_service.obrisiKategoriju(value).subscribe(
            data =>{this.catalog_service.getKategorije().subscribe(data => this.kategorije = data);},
            error => {alert("Greska")});
  }

  obrisiUslugu(value){
    this.catalog_service.obrisiUslugu(value).subscribe(
            data =>{this.catalog_service.getDodatneUsluge().subscribe(data => this.dodatneUsluge = data);},
            error => {alert("Greska")});
  }

  test(r1,r2,r3,id,ime,opis){
      console.log(r1,r2,r3);
      console.log(this.tipr,this.katr,this.uslr);
    if(r1 == true){
        var tip;
        if(this.editInProgress == true){
            tip = {
               hjid:id,
               nazivTipa:ime,
               opisTipa:opis
            }
        }else{
            tip = {
               hjid:null,
               nazivTipa:ime,
               opisTipa:opis
            }
        }
        
        console.log('snimanje tipa');
        this.catalog_service.snimiTip(tip).subscribe(
                data =>{this.catalog_service.getTipove().subscribe(data => this.tipovi = data);},
                error => {alert("Greska")});
    }else if (r2 == true){
        var kategorija;
        if(this.editInProgress == true){
            kategorija = {
               hjid:id,
               nazivKategorije:ime,
               opisKategorije:opis
            }
        }else{
            kategorija = {
               hjid:null,
               nazivKategorije:ime,
               opisKategorije:opis
            }
        }
        
        console.log('snimanje kategorije');
        this.catalog_service.snimiKategoriju(kategorija).subscribe(
                data =>{this.catalog_service.getKategorije().subscribe(data => this.kategorije = data);},
                error => {alert("Greska")});
    }else if (r3 == true){
        var usluga;
        if(this.editInProgress == true){
            usluga = {
               hjid:id,
               nazivUsluge:ime,
               opisUsluge:opis
            }
        }else{
            usluga = {
               hjid:null,
               nazivUsluge:ime,
               opisUsluge:opis
            }
        }
        console.log('snimanje usluge');
        this.catalog_service.snimiUslugu(usluga).subscribe(
                data =>{this.catalog_service.getDodatneUsluge().subscribe(data => this.dodatneUsluge = data);},
                error => {alert("Greska")});
    }else{
        alert('Greska ovde');
    }
    
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
    this.idtext = obj.hjid;
    this.nametext = obj.nazivTipa;
    this.opistext = obj.opisTipa;
  }

  setForEditK(obj){
    this.editInProgress = true;
    this.mode = "Edit";
    this.tipr = false;
    this.katr = true;
    this.uslr = false;
    this.idtext = obj.hjid;
    this.nametext = obj.nazivKategorije;
    this.opistext = obj.opisKategorije;
  }

  setForEditE(obj){
    this.editInProgress = true;
    this.mode = "Edit";
    this.tipr = false;
    this.katr = false;
    this.uslr = true;
    this.idtext = obj.hjid;
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
