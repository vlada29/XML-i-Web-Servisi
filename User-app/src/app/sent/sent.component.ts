import { Component, OnInit } from '@angular/core';
import { ProfileService } from '../services/profile.service';

@Component({
  selector: 'app-sent',
  templateUrl: './sent.component.html',
  styleUrls: ['./sent.component.css']
})
export class SentComponent implements OnInit {

  private sentMessages: any;
  private loggedUserId: any;

  constructor(private profileService: ProfileService) { }

  ngOnInit() {

      this.loggedUserId = localStorage.getItem('currentUserId');
      console.log(this.loggedUserId);

      this.profileService.getSentMessages(this.loggedUserId).subscribe(data =>
      this.sentMessages = data);

  }

}
