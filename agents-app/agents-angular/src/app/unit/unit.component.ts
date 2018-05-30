import {Component, Directive, Input, ViewChild, OnInit, ElementRef, AfterViewInit } from '@angular/core';
import { UnitService } from '../services/unit.service';
import {HttpClient} from "@angular/common/http";
import {LoginService} from '../services/login.service';
import {FormsModule} from '@angular/forms';
import {Observable} from 'rxjs/Observable';
import {UploadFileService} from '../upload-file.service';
import {NgbDateStruct, NgbCalendar, NgbModal} from '@ng-bootstrap/ng-bootstrap';
import 'rxjs/add/observable/of';

const equals = (one: NgbDateStruct, two: NgbDateStruct) =>
  one && two && two.year === one.year && two.month === one.month && two.day === one.day;

const before = (one: NgbDateStruct, two: NgbDateStruct) =>
  !one || !two ? false : one.year === two.year ? one.month === two.month ? one.day === two.day
    ? false : one.day < two.day : one.month < two.month : one.year < two.year;

const after = (one: NgbDateStruct, two: NgbDateStruct) =>
  !one || !two ? false : one.year === two.year ? one.month === two.month ? one.day === two.day
    ? false : one.day > two.day : one.month > two.month : one.year > two.year;

@Component({
  selector: 'app-unit',
  templateUrl: './unit.component.html',
  styleUrls: ['./unit.component.css'],
  styles: [
      `
    .custom-day {
      text-align: center;
      padding: 0.185rem 0.25rem;
      display: inline-block;
      height: 2rem;
      width: 2rem;
    }
    .custom-day.focused {
      background-color: #e6e6e6;
    }
    .custom-day.range, .custom-day:hover {
      background-color: rgb(2, 117, 216);
      color: white;
    }
    .custom-day.faded {
      background-color: rgba(2, 117, 216, 0.5);
    }
  `
  ]
})
export class UnitComponent implements OnInit {
  hoveredDate: NgbDateStruct;
  fromDate: NgbDateStruct;
  toDate: NgbDateStruct;
  dodatne_usluge: any;

   public fileUploadInUnit: Observable<string[]>
   public filesPathsOnly: string[];


   @ViewChild('myId') myId: ElementRef;

   public slike = [];
   getIndex(i){
       console.log('index je: ', i );
       this.slike = this.my_units[i].slike;
       console.log('slekotvane: ', this.slike);

}

  constructor(private unit_service: UnitService,
              private login_service: LoginService,
              private http: HttpClient,
              calendar: NgbCalendar,
              private uploadService: UploadFileService,
              private modalService: NgbModal
  ) {
      this.fromDate = calendar.getToday();
    this.toDate = calendar.getNext(calendar.getToday(), 'd', 10);
   }

  ngOnInit(){
    this.getMyUnits();
  }

  onDateSelection(date: NgbDateStruct) {
    if (!this.fromDate && !this.toDate) {

      this.fromDate = date;
    } else if (this.fromDate && !this.toDate && after(date, this.fromDate)) {
      this.toDate = date;
    } else {
      this.toDate = null;
      this.fromDate = date;
    }

    console.log(date);
  }

  isHovered = date => this.fromDate && !this.toDate && this.hoveredDate && after(date, this.fromDate) && before(date, this.hoveredDate);
  isInside = date => after(date, this.fromDate) && before(date, this.toDate);
  isFrom = date => equals(date, this.fromDate);
  isTo = date => equals(date, this.toDate);

  my_units: any;
  getMyUnits(){
      //this.http.get('/getMyUnits/'+this.login_service.username).subscribe(data => {
        this.http.get('/getMyUnits/'+'daca').subscribe(data => {
        if(data != null){
          console.log('My Units: ',data);
          this.my_units = data as any[];


        }
      })
  }
  cene = [];
  saveNewPricePlan(price){

    var new_plan = {
        //pocetakVazenja: this.fromDate.year+'-'+this.fromDate.month+'-'+this.fromDate.day+'T00:00:00',
        //krajVazenja: this.toDate.year+'-'+this.toDate.month+'-'+this.toDate.day+'T00:00:00',
        pocetakVazenja:  this.fromDate.year+'-'+this.fromDate.month+'-'+this.fromDate.day,
        krajVazenja: this.toDate.year+'-'+this.toDate.month+'-'+this.toDate.day,
        cena: price
    }
    this.cene.push(new_plan);
    console.log(this.cene);
  }

  fileToUpload: File = null;

  handleFileInput(files: FileList) {
    this.fileToUpload = files.item(0);
  }

  uploadFileToActivity() {
    this.postFile(this.fileToUpload).subscribe(data => {
      // do something, if upload success
      }, error => {
        console.log(error);
      });
  }

  postFile(fileToUpload: File): Observable<boolean> {
    const endpoint = 'your-destination-url';
    const formData: FormData = new FormData();
    formData.append('fileKey', fileToUpload, fileToUpload.name);
    return this.http
      .post(endpoint, formData )
      .map(() => { return true; })
      ;
}

  checkedT(){}
  checkedK(){}
  checkedE(){}

  createNewUnit(
      country,
      city,
      address,
      anumber,
      r1,r2,r3,
      opis,
      guests,
      breakfast, hp, p,
      parking, wifi, tv, mini, bathroom,
      nekat,z1,z2,z3,z4,z5,

  ){
      console.log('Creating new unit.');

      this.dodatne_usluge = [];

      var location = {
        drzava: country,
        grad: city,
        ulica: address,
        broj: anumber
      }

      let type;
      if(r1.checked) type='hotel';
      if(r2.checked) type='bad&breakfast';
      if(r3.checked) type='apartman';

      let food;
      if(breakfast.checked) food='breakfast';
      if(hp.checked) food='half-pansion';
      if(p.checked) food='pansion';

      let cat;
      if(nekat.checkedK) cat='Nekategorisan';
      if(z1) cat = 'One Star';
      if(z2) cat = 'Two Stars';
      if(z3) cat = 'Three Stars';
      if(z4) cat = 'Four Stars';
      if(z5) cat = 'Five Stars';

      var DodatneUsluge = {
        nazivUsluge: food,
        opisUsluge: 'temp opis'
      }

      this.dodatne_usluge.push(DodatneUsluge);

      this.popuniDodatne(parking,wifi,tv,mini,bathroom);

      var agent = {
          username: "coda",
          password: "daca",
          ime: "Danilo",
          prezime: "Bujisa",
          adresa: "Milice Tomic 14",
          poslovniMBR: 4323,
          hjid: 0
      }

      var tipSmestaja = {
          nazivTipa: type
      }

      var kategorijaSmestaja = {
          nazivKategorije: cat
      }

      var unit = {
        naziv: "none",
        agent: agent,
        lokacija: location,
        opis: opis,
        brojOsoba: guests,
        dodatneUsluge: this.dodatne_usluge,
        cene: this.cene,
        dostupna: true,
        slike: this.slike,
        kategorijaSmestaja: kategorijaSmestaja,
        tipSmestaja: tipSmestaja

      }
      this.clearInputs();
      console.log(unit);
      this.http.post('/createNewUnit/daca', unit).subscribe(data => {
          if(data != null){
              console.log('After creating: ');
              console.log(data);
              this.my_units = [];
              this.my_units = data as any[];
              //this.getMyUnits();
          } else {
              alert('Error while creating new unit!');
          }
       })


       this.filesPathsOnly = [];
  }

  popuniDodatne(parking,wifi,tv,mini,bathroom){
      var DodatneUsluge;
      if(parking.checked){
          DodatneUsluge = {
            nazivUsluge: 'parking',
            opisUsluge: 'temp opis'
          }
          this.dodatne_usluge.push(DodatneUsluge);
      }
      if(wifi.checked){
          DodatneUsluge = {
            nazivUsluge: 'wifi',
            opisUsluge: 'temp opis'
          }
          this.dodatne_usluge.push(DodatneUsluge);
      }
      if(tv.checked){
          DodatneUsluge = {
            nazivUsluge: 'tv',
            opisUsluge: 'temp opis'
          }
          this.dodatne_usluge.push(DodatneUsluge);
      }
      if(mini.checked){
          DodatneUsluge = {
            nazivUsluge: 'mini',
            opisUsluge: 'temp opis'
          }
          this.dodatne_usluge.push(DodatneUsluge);
      }
      if(bathroom.checked){
          DodatneUsluge = {
            nazivUsluge: 'bathroom',
            opisUsluge: 'temp opis'
          }
          this.dodatne_usluge.push(DodatneUsluge);
      }
  }



  confirmRes(){

  }

  clearInputs(){
      this.dodatne_usluge = [];
  }
odustani(){
    this.filesPathsOnly = [];

}
  change(item){

  }




@ViewChild('myModal') myModal:ElementRef;
  getReady(){
      console.log('getReady');
      this.slike = [];
      $(this.myModal.nativeElement).modal('show');
  }

  @ViewChild('modalZauzimanje') modalZauzimanje:ElementRef;

  zauzetost = null;
   zauzmi(i){
       console.log('reserving');
       $(this.modalZauzimanje.nativeElement).modal('show');



   }

   saveZauzetost(){

       // var hjid = 68;//this.my_units[i].hjid;
       var unit = {
        hjid: 13
       }

       this.zauzetost = {
           smestajnaJedinica: unit,
           //od: this.fromDate.year+'-'+this.fromDate.month+'-'+this.fromDate.day+'T00:00:00',
           //do: this.toDate.year+'-'+this.toDate.month+'-'+this.toDate.day+'T00:00:00'

           od: this.fromDate.year+'-'+this.fromDate.month+'-'+this.fromDate.day,
           do: this.toDate.year+'-'+this.toDate.month+'-'+this.toDate.day
       }

       this.http.post('/reserve', this.zauzetost).subscribe(data => {
           this.getMyUnits();
       })
   }
}

declare var $:any;
