import { Component, OnInit } from '@angular/core';
import { CommentServiceService } from '../services/comment-service.service';

@Component({
  selector: 'app-comment-editing',
  templateUrl: './comment-editing.component.html',
  styleUrls: ['./comment-editing.component.css']
})
export class CommentEditingComponent implements OnInit {
  private comments;

  constructor(private comment_service: CommentServiceService ) { }

  ngOnInit() {
    this.comment_service.getComments().subscribe(data => this.comments = data);
  }

  approve(com){
    this.comment_service.approveComment(com).subscribe(
            data => {this.comment_service.getComments().subscribe(data => this.comments = data);},
            error => {this.comment_service.getComments().subscribe(data => this.comments = data);} 
          );
  }

  remove(com){
    this.comment_service.deleteComment(com).subscribe(
            data => {this.comment_service.getComments().subscribe(data => this.comments = data);},
            error => {this.comment_service.getComments().subscribe(data => this.comments = data);});
  }
}
