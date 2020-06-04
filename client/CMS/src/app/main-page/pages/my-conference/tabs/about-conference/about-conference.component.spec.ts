import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { AboutConferenceComponent } from './about-conference.component';

describe('AboutConferenceComponent', () => {
  let component: AboutConferenceComponent;
  let fixture: ComponentFixture<AboutConferenceComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ AboutConferenceComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(AboutConferenceComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
