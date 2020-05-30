import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { AddProposalComponent } from './add-proposal.component';

describe('AddProposalComponent', () => {
  let component: AddProposalComponent;
  let fixture: ComponentFixture<AddProposalComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ AddProposalComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(AddProposalComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
