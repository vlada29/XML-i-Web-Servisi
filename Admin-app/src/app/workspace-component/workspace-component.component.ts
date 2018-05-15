import { Component, OnInit } from '@angular/core';

@Component({
  selector: 'app-workspace-component',
  templateUrl: './workspace-component.component.html',
  styleUrls: ['./workspace-component.component.css']
})
export class WorkspaceComponentComponent implements OnInit {

  constructor() { }

  ngOnInit() {
  }

  logout(){
    console.log('logout admin');
  }

}
