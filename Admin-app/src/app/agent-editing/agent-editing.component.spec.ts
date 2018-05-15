import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { AgentEditingComponent } from './agent-editing.component';

describe('AgentEditingComponent', () => {
  let component: AgentEditingComponent;
  let fixture: ComponentFixture<AgentEditingComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ AgentEditingComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(AgentEditingComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
