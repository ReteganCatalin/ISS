import {Component, Input, OnInit} from '@angular/core';

@Component({
  selector: 'bidding-page',
  templateUrl: './bidding-page.component.html',
  styleUrls: ['./bidding-page.component.css']
})
export class BiddingPageComponent{
  @Input("conferenceID") conferenceID;
  constructor() {
  }

}
