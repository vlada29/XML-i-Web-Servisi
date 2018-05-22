import { Injectable } from '@angular/core';
import { IUser } from '../interfaces/IUser';
import { HttpClient } from '@angular/common/http';
import { IKomentar } from '../interfaces/IKomentar';
import { IOcena } from '../interfaces/IOcena';
import { Observable } from "rxjs/Observable";

@Injectable()
export class UserServiceService {

  private users = null;

  constructor(private http: HttpClient) { }

  public getUsers(): Observable<IUser[]>{
    return this.http.get<IUser[]>('/getAllUsers');
  }

  public removeUser(user): Observable<any>{
    return this.http.post('/removeUser',user);
  }

  public activateUser(user): Observable<any>{
    return this.http.post('/activateUser',user);
  }

  public blockUser(user): Observable<any>{
    return this.http.post('/blockUser',user);
  }

}
