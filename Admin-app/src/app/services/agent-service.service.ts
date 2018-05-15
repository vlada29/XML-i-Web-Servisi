import { Injectable } from '@angular/core';
import { IAgent } from '../interfaces/IAgent';

@Injectable()
export class AgentServiceService {
  private agents = null;
  
  constructor() { }

  public getAgents():IAgent[]{
    this.agents = [{
      username: 'Agent1', password: 'pas1', ime: 'Firstname1', prezime: 'Lastname1', adresa: 'Adress1', poslovniMBR: '20201'
    },{
      username: 'Agent2', password: 'pas2', ime: 'Firstname2', prezime: 'Lastname2', adresa: 'Adress2', poslovniMBR: '20202'
    },{
      username: 'Agent3', password: 'pas3', ime: 'Firstname3', prezime: 'Lastname3', adresa: 'Adress3', poslovniMBR: '20203'
    }];

    return this.agents;
  }

  public createAgent(agent){
    console.log(agent);
  }
}
