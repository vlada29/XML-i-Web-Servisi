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
    this.user_service.activateUser(user).subscribe(
            data => {this.user_service.getUsers().subscribe(data => this.users = data);},
            error => {alert("Greska")});
  }

  block(user){
    this.user_service.blockUser(user).subscribe(
            data => {this.user_service.getUsers().subscribe(data => this.users = data);},
            error => {alert("Greska")});
  }

  remove(user){
    this.user_service.removeUser(user).subscribe(
            data => {this.user_service.getUsers().subscribe(data => this.users = data);},
            error => {alert("Greska")});
  }


}
