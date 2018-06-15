import { Component, OnInit } from '@angular/core';
import { ProfileService } from '../services/profile.service';

@Component({
  selector: 'app-inbox',
  templateUrl: './inbox.component.html',
  styleUrls: ['./inbox.component.css']
})
export class InboxComponent implements OnInit {

  private receivedMessages: any;
  private loggedUserId: any;

  constructor(private profileService: ProfileService) { }


  ngOnInit() {

    this.loggedUserId = localStorage.getItem('currentUserId');
    console.log(this.loggedUserId);

    this.profileService.getReceivedMessages(this.loggedUserId).subscribe(data =>
    this.receivedMessages = data);
  }



}
