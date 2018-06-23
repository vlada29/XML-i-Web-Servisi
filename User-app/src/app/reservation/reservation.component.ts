import { Component, OnInit } from '@angular/core';
import { ActivatedRoute, Router } from '@angular/router';
import { HomeService } from '../services/home.service';
import { RatingService } from '../services/rating.service';
import { Observable } from 'rxjs/Observable';
import { DomSanitizer, SafeResourceUrl, SafeUrl} from '@angular/platform-browser';


@Component({
  selector: 'app-reservation',
  templateUrl: './reservation.component.html',
  styleUrls: ['./reservation.component.css']
})
export class ReservationComponent implements OnInit {

  private par: any;
  private r: any; // selected reservation
  private comments: any[];
  private ocena: any;

  constructor(private route: ActivatedRoute,
  private homeService: HomeService,
  private router: Router,
  private ratingService: RatingService) { }

  ngOnInit() {

    this.route.queryParams
    .filter(params => params.id)
    .subscribe(params => {

      this.par = params.id;
      console.log(this.par); // samo id

      this.homeService.getReservation(this.par).subscribe(data =>
       {this.r = data;
        console.log(this.r);

        console.log(this.r.smestajnaJedinica.hjid);

        this.homeService.getCom(this.r.smestajnaJedinica.hjid).subscribe(data =>
          {
        this.comments = data;
        console.log("komentari");
        console.log(this.comments);
        this.createUrls(this.r.smestajnaJedinica.hjid);
        this.createUrls(this.r.smestajnaJedinica.hjid);

        this.ratingService.getOcena(this.r.smestajnaJedinica.hjid).subscribe(data =>
        this.ocena = data)
      });
       }
      );





    });



    
  }

  messageAgent(r: any){
    //username = email
    console.log(r.smestajnaJedinica.agent.username);
    if (r.realizovana==true){
      this.router.navigate(['/message'],  
      { queryParams: { id: "-1", username: r.smestajnaJedinica.agent.username}, 
      });
    }


  }

    // slike

    image_urls = [];
    image_to_show: any;
    binaryData = [];
    show_images = false;
    public o_images: Observable<any>
    b64strings = [];
  
    createUrls( hjid){
      console.log("createurls usao");
      var x = document.getElementById(hjid);
      if (x.style.display === "none") {
          x.style.display = "block";
      } else {
          x.style.display = "none";
      }

  
      var urlCreator = window.URL; // || window.webkitURL;
      this.image_urls = [];
      this.b64strings = [];
      for(let image_blob of this.r.smestajnaJedinica.picture){
          this.b64strings.push(image_blob);
          this.binaryData = [];
          this.binaryData.push(image_blob);
          this.image_to_show = urlCreator.createObjectURL(new Blob(this.binaryData, {type: "application/zip"}));
          this.image_urls.push(this.image_to_show);
  
  
      }
      console.log(this.b64strings);
      this.o_images =  Observable.of(this.image_urls);
      this.show_images = true;
  
  // } else {
   //   this.show_images = false;
  // }
  
  }

}
