import { Component, OnInit } from '@angular/core';
import { ProfileService } from '../services/profile.service';
import { Router } from '@angular/router';

@Component({
  selector: 'app-inbox',
  templateUrl: './inbox.component.html',
  styleUrls: ['./inbox.component.css']
})
export class InboxComponent implements OnInit {

  private receivedMessages: any;
  private loggedUserId: any;

  private selectMessage: boolean = false;
  private agentSel: any;
  private naslovSel: any;
  private contentSel: any;
  private messid: any;

  constructor(private profileService: ProfileService, private router: Router) { }


  ngOnInit() {

    this.loggedUserId = localStorage.getItem('currentUserId');
    console.log(this.loggedUserId);

    this.profileService.getReceivedMessages(this.loggedUserId).subscribe(data =>
    this.receivedMessages = data);
  }

  showMessage(message: any){
 
    this.agentSel = message.agent.username;
    this.naslovSel = message.naslov;
    this.contentSel = message.content;
    this.messid = message.hjid;

    this.selectMessage = true;
  }

  x(){
    this.selectMessage = false;
  }

  reply(messid: any){
    console.log(messid);
    this.router.navigate(['/message'],  
    { queryParams: { id: messid}, 
    });
    
  }



}
