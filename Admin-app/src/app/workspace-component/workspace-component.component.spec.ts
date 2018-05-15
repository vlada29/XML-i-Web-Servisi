import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { WorkspaceComponentComponent } from './workspace-component.component';

describe('WorkspaceComponentComponent', () => {
  let component: WorkspaceComponentComponent;
  let fixture: ComponentFixture<WorkspaceComponentComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ WorkspaceComponentComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(WorkspaceComponentComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
