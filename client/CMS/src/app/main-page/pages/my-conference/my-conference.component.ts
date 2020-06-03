import {AfterContentInit, ChangeDetectorRef, Component, ContentChildren, Input, OnInit} from '@angular/core';
import {BiddingPageComponent} from "./tabs/bidding-page/bidding-page.component";
import { ActivatedRoute } from '@angular/router';

@Component({
  selector: 'app-my-conference',
  templateUrl: './my-conference.component.html',
  styleUrls: ['./my-conference.component.css']
})
export class MyConferenceComponent implements AfterContentInit{
  @ContentChildren(BiddingPageComponent) biddingPage: BiddingPageComponent;
  @Input("conferenceID") conferenceID;
  constructor(private routes: ActivatedRoute,
              private  changeDetectorRef: ChangeDetectorRef) {

  }

  ngAfterContentInit() {
  }
}
