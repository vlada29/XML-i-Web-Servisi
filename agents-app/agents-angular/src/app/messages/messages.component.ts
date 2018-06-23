import {Component, Directive, Input, ViewChild, OnInit, ElementRef, AfterViewInit } from '@angular/core';
import {HttpClient} from "@angular/common/http";
import {LoginService} from '../services/login.service';
@Component({
  selector: 'app-messages',
  templateUrl: './messages.component.html',
  styleUrls: ['./messages.component.css']
})
export class MessagesComponent implements OnInit {

  constructor(private http: HttpClient, private login_service: LoginService) { }

  ngOnInit(){
    this.getMyMessages();
  }

  my_messages: any;
  getMyMessages(){
      //this.http.get('/getMyUnits/'+this.login_service.username).subscribe(data => {
      this.http.get('/getMessages/'+this.login_service.user.hjid).subscribe(data => {
        if(data != null){
          console.log('My Messages: ',data);
          this.my_messages = data as any[];


        }
      })
  }

  from: any;
  contentrec:any;
  fromId: any;
  naslov: any;
  @ViewChild('openMessageModule') openMessageModule:ElementRef;

   openMessage(message){
       this.from = message.user.username;
       this.contentrec = message.content;
       this.fromId = message.user.hjid;
       this.naslov = message.naslov;
       $(this.openMessageModule.nativeElement).modal('show');



   }

    sentcontent: any;
    @ViewChild('sentModal') sentModal:ElementRef;

     openSentMessage(content){
         this.sentcontent = content;
         $(this.sentModal.nativeElement).modal('show');



     }

   reply(replycontent){
       //this.http.get('/getMyUnits/'+this.login_service.username).subscribe(data => {
         var agent = {
            hjid: 0
         }
         var user = {
            hjid: this.fromId,
            username: this.from,
            active: true
         }
         var message = {
            agent: agent,
            user: user,
            content: replycontent.value,
            naslov: this.naslov,
            senderType: 'Agent'
         }
         this.http.post('/sendMessage', message).subscribe(data => {
         if(data != null){

           console.log('Message: ', message);
           this.getMyMessages();


         }
       })
   }

   odustani(){}
}
declare var $:any;
