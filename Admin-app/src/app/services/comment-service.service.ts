import { Injectable } from '@angular/core';
import { Observable } from "rxjs/Observable";
import { HttpClient } from '@angular/common/http';
import { ICommentWrapper } from "../interfaces/ICommentWrapper";

@Injectable()
export class CommentServiceService {
  private comments = null;

  constructor(private http: HttpClient) { }

  public getComments(): Observable<ICommentWrapper[]>{
      return this.http.get<ICommentWrapper[]>('/getComments');
  }

  public approveComment(com): Observable<any>{
      var endpoint = "https://us-central1-rating-system-ca802.cloudfunctions.net/approveComment";
      
      return this.http.post(endpoint,com);
  }
  
  public deleteComment(com): Observable<any>{
      var endpoint = "https://us-central1-rating-system-ca802.cloudfunctions.net/deleteComment";
      
      return this.http.post(endpoint,com);
  }

}
