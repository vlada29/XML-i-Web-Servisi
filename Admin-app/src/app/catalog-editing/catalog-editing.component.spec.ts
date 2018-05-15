import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { CatalogEditingComponent } from './catalog-editing.component';

describe('CatalogEditingComponent', () => {
  let component: CatalogEditingComponent;
  let fixture: ComponentFixture<CatalogEditingComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ CatalogEditingComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(CatalogEditingComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
