import { Injectable } from '@angular/core';
import { IUser } from '../interfaces/IUser';

@Injectable()
export class UserServiceService {

  private users = null;

  constructor() { }

  public getUsers():IUser[]{
    this.users = [{
      ime: 'Vlada',prezime: 'Djurdjevic', username: 'Dovla29', password: 'Dovla29', active: true
    },{
      ime: 'Danilo',prezime: 'Bujisa', username: 'qanilo', password: 'qanilo', active: true
    },{
      ime: 'Olivera',prezime: 'Hrnjakovic', username: 'OljaH', password: 'OljaH', active: false
    }];

    return this.users;
  }

  public removeUser(user){
    
  }

  public activateUser(user){

  }

  public blockUser(user){

  }

}
