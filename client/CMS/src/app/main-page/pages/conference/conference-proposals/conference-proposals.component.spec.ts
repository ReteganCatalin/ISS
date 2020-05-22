import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { ConferenceProposalsComponent } from './conference-proposals.component';

describe('ConferenceProposalsComponent', () => {
  let component: ConferenceProposalsComponent;
  let fixture: ComponentFixture<ConferenceProposalsComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ ConferenceProposalsComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(ConferenceProposalsComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
