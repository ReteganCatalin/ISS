import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { ConferenceSectionsComponent } from './conference-sections.component';

describe('ConferenceSectionsComponent', () => {
  let component: ConferenceSectionsComponent;
  let fixture: ComponentFixture<ConferenceSectionsComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ ConferenceSectionsComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(ConferenceSectionsComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
