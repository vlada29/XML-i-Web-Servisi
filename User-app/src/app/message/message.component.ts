import { Component, OnInit } from '@angular/core';
import { ProfileService } from '../services/profile.service';
import { LoginServiceService } from '../services/login-service.service';

@Component({
  selector: 'app-message',
  templateUrl: './message.component.html',
  styleUrls: ['./message.component.css']
})
export class MessageComponent implements OnInit {

  private email: any;
  private tekst: any;

  private loggedUserId: any;
  private loggedUser: any;

  constructor(private profileService: ProfileService, private loginService: LoginServiceService) { }

  ngOnInit() {
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
                         "user": this.loggedUser.username
 
                     };
    this.profileService.sendMessage(message).subscribe();
  }

}
