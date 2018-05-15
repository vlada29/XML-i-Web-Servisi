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
    this.comments = this.comment_service.getComments();
  }

  approve(com){
    console.log('Approve ',com.idKomentara)
  }

  remove(com){
    console.log('Remove ',com.idKomentara)
  }
}
