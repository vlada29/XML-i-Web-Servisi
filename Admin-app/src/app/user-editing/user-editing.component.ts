import { Component, OnInit } from '@angular/core';
import { UserServiceService } from '../services/user-service.service';

@Component({
  selector: 'app-user-editing',
  templateUrl: './user-editing.component.html',
  styleUrls: ['./user-editing.component.css']
})
export class UserEditingComponent implements OnInit {
  private users;

  constructor(private user_service: UserServiceService) { }

  ngOnInit() {
    this.user_service.getUsers().subscribe(data => this.users = data);
  }

  activate(user){
    console.log('Activate ',user.username);
    this.user_service.activateUser(user);
  }

  block(user){
    console.log('Block ',user.username);
    this.user_service.blockUser(user);
  }

  remove(user){
    console.log('Remove ',user.username);
    this.user_service.removeUser(user);
  }


}
