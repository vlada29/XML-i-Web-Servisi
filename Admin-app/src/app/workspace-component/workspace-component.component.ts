import { Component, OnInit } from '@angular/core';
import { Router } from '@angular/router';

@Component({
  selector: 'app-workspace-component',
  templateUrl: './workspace-component.component.html',
  styleUrls: ['./workspace-component.component.css']
})
export class WorkspaceComponentComponent implements OnInit {

  constructor(private router: Router) { }

  ngOnInit() {
  }

  logout(){
    this.router.navigate(['login']);
  }

}
