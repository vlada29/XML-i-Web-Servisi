import { Component, EventEmitter, OnInit, Output  } from '@angular/core';
import { LoginServiceService } from '../services/login-service.service';
import { ActivatedRoute } from '@angular/router';
import 'rxjs/add/operator/filter'




@Component({
  selector: 'app-home',
  templateUrl: './home.component.html',
  styleUrls: ['./home.component.css']
})
export class HomeComponent implements OnInit {

  private loggedUser: any;
  private loggedUserId: any;

  constructor(private loginService: LoginServiceService,
    private route: ActivatedRoute) { }

  ngOnInit() {

    // za security - ne radi
    // this.loginService.getLoggedUser().subscribe(data =>
    // this.loggedUser = data);

    this.loggedUserId = localStorage.getItem('currentUserId');
    console.log(this.loggedUserId);

    this.loginService.getLoggedUserById(this.loggedUserId).subscribe(data =>
      this.loggedUser = data);
  }

}
