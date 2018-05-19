import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs/Observable';
import { IAgent } from '../interfaces/IAgent';
import { IUser } from "../interfaces/IUser";

@Injectable()
export class AgentServiceService {
  
  constructor(private http: HttpClient) { }

  public getAgents():Observable<IAgent[]>{
    return this.http.get<IAgent[]>('/getAllAgents');
  }

  public createAgent(agent){
    this.http.post('/saveAgent',agent).subscribe(
       data => {}, 
       error => { alert("Greska"); } 
    )
  }

}
