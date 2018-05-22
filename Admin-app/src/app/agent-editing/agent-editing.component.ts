import { Component, OnInit } from '@angular/core';
import { AgentServiceService } from '../services/agent-service.service';
import { IAgent } from '../interfaces/IAgent';

@Component({
  selector: 'app-agent-editing',
  templateUrl: './agent-editing.component.html',
  styleUrls: ['./agent-editing.component.css']
})
export class AgentEditingComponent implements OnInit {
  private agents;
  private user = null;
  
  constructor(private agent_service: AgentServiceService) { }

  ngOnInit() {
    this.agent_service.getAgents().subscribe(data => this.agents=data);
  }

  test(f,l,u,p,a,pi){
    var agent:IAgent = {
      ime:f,
      prezime:l,
      username:u,
      password:p,
      adresa:a,
      poslovniMBR:pi,
      hjid: null
    }

    this.agent_service.createAgent(agent).subscribe(
            data => {this.agent_service.getAgents().subscribe(data => this.agents=data);},
            error => {alert("Greska")});
  }
}
