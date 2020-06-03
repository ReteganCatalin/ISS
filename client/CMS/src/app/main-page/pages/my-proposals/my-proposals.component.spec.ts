import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { MyProposalsComponent } from './my-proposals.component';

describe('MyProposalsComponent', () => {
  let component: MyProposalsComponent;
  let fixture: ComponentFixture<MyProposalsComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ MyProposalsComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(MyProposalsComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
