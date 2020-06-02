import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { EditProposalComponent } from './edit-proposal.component';

describe('EditProposalComponent', () => {
  let component: EditProposalComponent;
  let fixture: ComponentFixture<EditProposalComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ EditProposalComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(EditProposalComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
