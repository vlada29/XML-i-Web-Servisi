import { Injectable } from '@angular/core';
import { IKomentar } from '../interfaces/IKomentar';
import { Observable } from "rxjs/Observable";
import { HttpClient } from '@angular/common/http';

@Injectable()
export class CommentServiceService {
  private comments = null;

  constructor(private http: HttpClient) { }

  public getComments(): Observable<IKomentar[]>{
      return this.http.get<IKomentar[]>('/getComments');
  }


}
