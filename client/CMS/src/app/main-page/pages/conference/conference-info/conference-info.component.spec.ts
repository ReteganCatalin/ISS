import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { ConferenceInfoComponent } from './conference-info.component';

describe('ConferenceInfoComponent', () => {
  let component: ConferenceInfoComponent;
  let fixture: ComponentFixture<ConferenceInfoComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ ConferenceInfoComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(ConferenceInfoComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
