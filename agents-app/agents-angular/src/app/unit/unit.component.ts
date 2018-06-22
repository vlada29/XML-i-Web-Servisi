import {Component, Directive, Input, ViewChild, OnInit, ElementRef, AfterViewInit, Pipe, PipeTransform } from '@angular/core';
import { UnitService } from '../services/unit.service';
import {HttpClient} from "@angular/common/http";
import {LoginService} from '../services/login.service';
import {FormsModule} from '@angular/forms';
import {Observable} from 'rxjs/Observable';
import {UploadFileService} from '../upload-file.service';
import {NgbDateStruct, NgbCalendar, NgbModal} from '@ng-bootstrap/ng-bootstrap';
import 'rxjs/add/observable/of';
import { DomSanitizer, SafeResourceUrl, SafeUrl} from '@angular/platform-browser';

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
              private modalService: NgbModal,
              private _sanitizer: DomSanitizer
  ) {
      this.fromDate = calendar.getToday();
    this.toDate = calendar.getNext(calendar.getToday(), 'd', 10);
   }

  ngOnInit(){
    this.getMyUnits();
    this.getCats();
    this.getTypes();
    this.getExtras();
    this.getResOfMyUnits();
    //$('.selectpicker').selectpicker('refresh');
    // setTimeout(() => {
    //   $('.selectpicker').selectpicker('refresh');
    // }, 500);
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
      this.http.get('/getMyUnits/'+this.login_service.user.username).subscribe(data => {
       // this.http.get('/getMyUnits/'+'daca').subscribe(data => {
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


  createNewUnit(
      country,
      city,
      address,
      anumber,
      opis,
      guests



  ){
      console.log('Creating new unit.');

      this.dodatne_usluge = [];

      var location = {
        drzava: country,
        grad: city,
        ulica: address,
        broj: anumber
      }

      for(let c of this.cats){
          if(c.nazivKategorije == this.selectedCat){
              this.catObj = c;
              break;
          }
      }

      for(let t of this.types){
          if(t.nazivTipa == this.selectedType){
              this.typeObj = t;
              break;
          }
      }

      //this.dodatne_usluge.push(DodatneUsluge);


      var agent = {
         username: "daca",
         // password: "daca",
         // ime: "Danilo",
         // prezime: "Bujisa",
         // adresa: "Milice Tomic 14",
         // poslovniMBR: 4323,
          hjid: 0
      }

      var usluge = [];
      for(let u of this.extrasModelProperty){
          var uslugaJedinice = {
              usluga: u
          }
          usluge.push(uslugaJedinice);
      }
      console.log('usluge: ', usluge);
      var unit = {
        naziv: "none",
        agent: agent,
        lokacija: location,
        opis: opis,
        brojOsoba: guests,
        usluge: usluge,
        cene: this.cene,
        dostupna: true,
        slike: this.slike,
        kategorijaSmestaja: this.catObj,
        tipSmestaja: this.typeObj

      }
      this.clearInputs();
      console.log(unit);

      if(this.slike.length > 0){
          this.http.post('/createNewUnit/'+this.login_service.user.username, unit).subscribe(data => {
              if(data != null){
                  console.log('After creating: ');
                  console.log(data);
                  this.my_units = [];
                 // this.my_units = data as any[];
                  this.getMyUnits();
                  this.cene=[];
           	  this.show_images = false; 	
              } else {
              	  this.cene=[];
                  alert('Error while creating new unit!');
              }
           })
       } else {
           alert('You need to upload atleast one image!');
       }

  this.show_images = false; 	
       this.filesPathsOnly = [];
  }

  catObj: any;
  typeObj: any;

  selectedCat: any;
  selectedType: any;

  catChanged(catValue){
      this.selectedCat = catValue;
  }

  typeChanged(typeValue){
      this.selectedType = typeValue;
  }

  extrasModelProperty = [];
  printExtras(){
          console.log('extrasModelProperty ',this.extrasModelProperty);
  }

  types: any;
  cats: any;
  extras: any;

  getTypes(){
      this.http.get('/getTypes').subscribe(data => {
      if(data != null){
        console.log('Type: ',data);
        this.types = data as any[];
        this.typeObj = data[0];



      }
    })
  }

  getCats(){
      this.http.get('/getCategories').subscribe(data => {
      if(data != null){
        console.log('Cats: ',data);
        this.cats = data as any[];
        this.catObj = data[0];

      }
    })

  }

  getExtras(){
      this.http.get('/getExtras').subscribe(data => {
      if(data != null){
        console.log('Extras: ',data);
        this.extras = data as any[];
        // var temp;
        // for(let i in this.extras){
        //     this.temp.push(i.nazivUsluge);
        // }
        this.extrasModelProperty = this.extras;//.split(',');
        console.log('extrasModelProperty ',this.extrasModelProperty);
      }
      })

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

  rezervacija = null;
   zauzmi(i){
       console.log('reserving row: ', i);
       this.row_z = i;
       $(this.modalZauzimanje.nativeElement).modal('show');



   }
   row_z: any;
   saveZauzetost(){

       var unit = {
        hjid: this.my_units[this.row_z].hjid
       }

       this.rezervacija = {
          
           //od: this.fromDate.year+'-'+this.fromDate.month+'-'+this.fromDate.day+'T00:00:00',
           //do: this.toDate.year+'-'+this.toDate.month+'-'+this.toDate.day+'T00:00:00'
			smestajnaJedinica: unit,
           od: this.fromDate.year+'-'+this.fromDate.month+'-'+this.fromDate.day,
           do: this.toDate.year+'-'+this.toDate.month+'-'+this.toDate.day
       }

       this.http.post('/reserve' ,this.rezervacija).subscribe(data => {
       	if(data!=null){
           this.getMyUnits();
           this.getResOfMyUnits();
           }else{
           alert('Already reserved for given dates!');
           }
       })
   }
   
   res_of_my_units : any;
  getResOfMyUnits(){
      this.http.get('/getResOfMyUnits/'+this.login_service.user.username).subscribe(data => {
      if(data != null){
        console.log('Res of my units: ',data);
        this.res_of_my_units = data as any[];
		this.updateZauzetost();

      }
    })
  }

	updateZauzetost(){
		for(let unit of this.my_units){
			for(let res of this.res_of_my_units){	
				if(res.smestajnaJedinica.hjid == unit.hjid){
					var zauz = {
						od: res.od,
						do: res.do
					}
					unit.listaZauzetosti.push(zauz);
				}
			}
			
		}
	}



   image_urls = [];
   image_to_show: any;
   binaryData = [];
   show_images = false;

   public o_images: Observable<any>
   b64strings = [];
   createUrls(i, hjid){
       var x = document.getElementById(hjid);
       if (x.style.display === "none") {
           x.style.display = "block";
       } else {
           x.style.display = "none";
       }

      this.my_units.forEach((item, index) => {
            if(index!=i){
                x = document.getElementById(item.hjid);
                x.style.display = "none";
            }
        });





       var urlCreator = window.URL; // || window.webkitURL;
       this.image_urls = [];
       this.b64strings = [];
       for(let image_blob of this.my_units[i].picture){
           this.b64strings.push(image_blob);
           this.binaryData = [];
           this.binaryData.push(image_blob);
           this.image_to_show = urlCreator.createObjectURL(new Blob(this.binaryData, {type: "application/zip"}));
           this.image_urls.push(this.image_to_show);


       }
       console.log(this.image_urls);
       this.o_images =  Observable.of(this.image_urls);
       this.show_images = true;
  // } else {
    //   this.show_images = false;
  // }

   }

   approveImage(toReturnImage){
       return this._sanitizer.bypassSecurityTrustResourceUrl('data:image/jpg;base64,'
                 + toReturnImage);

   }


}

declare var $:any;
