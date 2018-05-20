import { Component, OnInit } from '@angular/core';
import { UnitService } from '../services/unit.service';
@Component({
  selector: 'app-unit',
  templateUrl: './unit.component.html',
  styleUrls: ['./unit.component.css']
})
export class UnitComponent implements OnInit {

  constructor(private unit_service: UnitService) { }

  ngOnInit(){}
  checkedT(){}
  checkedK(){}
  checkedE(){}
  createNewUnit(){
      console.log('Creating new unit.');
      this.unit_service.createNewUnit();
  }
}
