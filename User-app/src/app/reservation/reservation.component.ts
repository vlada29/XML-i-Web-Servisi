import { Component, OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';
import { HomeService } from '../services/home.service';


@Component({
  selector: 'app-reservation',
  templateUrl: './reservation.component.html',
  styleUrls: ['./reservation.component.css']
})
export class ReservationComponent implements OnInit {

  private par: any;
  private r: any; // selected reservation

  constructor(private route: ActivatedRoute,
  private homeService: HomeService) { }

  ngOnInit() {

    this.route.queryParams
    .filter(params => params.id)
    .subscribe(params => {

      this.par = params.id;
      console.log(this.par); // samo id

      this.homeService.getReservation(this.par).subscribe(data =>
       {this.r = data;
        console.log(this.r);
       }
      );

    });

    
  }

}
