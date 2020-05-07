import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { ConferencesPageComponent } from './conferences-page.component';

describe('ConferencesPageComponent', () => {
  let component: ConferencesPageComponent;
  let fixture: ComponentFixture<ConferencesPageComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ ConferencesPageComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(ConferencesPageComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
