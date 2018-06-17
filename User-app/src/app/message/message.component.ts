import { Component, OnInit } from '@angular/core';
import { ProfileService } from '../services/profile.service';
import { LoginServiceService } from '../services/login-service.service';
import { ActivatedRoute, Router } from '@angular/router';

@Component({
  selector: 'app-message',
  templateUrl: './message.component.html',
  styleUrls: ['./message.component.css']
})
export class MessageComponent implements OnInit {

  private email: any;
  private tekst: any;
  private naslov: any;

  private loggedUserId: any;
  private loggedUser: any;

  private par: any;
  private selMessageReply: any;

  constructor(private profileService: ProfileService, private loginService: LoginServiceService,
  private route: ActivatedRoute,
  private router: Router) { }

  ngOnInit() {
    this.route.queryParams
    .filter(params => params.id)
    .subscribe(params => {

      this.par = params.id;
      console.log("par"+this.par); // samo id

      this.email = params.username;
      
      if (this.par!="-1"){
        this.profileService.getMessage(this.par).subscribe(data =>
        {this.selMessageReply = data;
        this.email = data.agent.username;
        this.naslov = "RE: "+data.naslov;
        })
      }

    });

    this.loggedUserId = localStorage.getItem('currentUserId');
    console.log(this.loggedUserId);

    this.loginService.getLoggedUserById(this.loggedUserId).subscribe(data =>
      this.loggedUser = data);

  }

  send(){
    console.log(this.email);
    console.log(this.tekst);
    var message = 
                     {
                         "content": this.tekst,
                         "agent": this.email,
                         "user": this.loggedUser.username,
                         "naslov": this.naslov
 
                     };
    this.profileService.sendMessage(message).subscribe();
    this.router.navigate(['/sent']);
  }

}
