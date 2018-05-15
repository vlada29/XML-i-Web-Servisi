import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { CommentEditingComponent } from './comment-editing.component';

describe('CommentEditingComponent', () => {
  let component: CommentEditingComponent;
  let fixture: ComponentFixture<CommentEditingComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ CommentEditingComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(CommentEditingComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
