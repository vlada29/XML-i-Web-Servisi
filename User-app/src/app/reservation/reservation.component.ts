import { Component, OnInit } from '@angular/core';
import { ActivatedRoute, Router } from '@angular/router';
import { HomeService } from '../services/home.service';
import { RatingService } from '../services/rating.service';


@Component({
  selector: 'app-reservation',
  templateUrl: './reservation.component.html',
  styleUrls: ['./reservation.component.css']
})
export class ReservationComponent implements OnInit {

  private par: any;
  private r: any; // selected reservation
  private comments: any[];

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

}
