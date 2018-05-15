import { TestBed, inject } from '@angular/core/testing';

import { AgentServiceService } from './agent-service.service';

describe('AgentServiceService', () => {
  beforeEach(() => {
    TestBed.configureTestingModule({
      providers: [AgentServiceService]
    });
  });

  it('should be created', inject([AgentServiceService], (service: AgentServiceService) => {
    expect(service).toBeTruthy();
  }));
});
