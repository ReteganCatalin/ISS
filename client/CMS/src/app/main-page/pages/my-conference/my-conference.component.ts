import {AfterContentInit, ChangeDetectorRef, Component, ContentChildren, Input, OnInit} from '@angular/core';
import {BiddingPageComponent} from "./tabs/bidding-page/bidding-page.component";
import { ActivatedRoute } from '@angular/router';
import {HttpClient} from "@angular/common/http";

@Component({
  selector: 'app-my-conference',
  templateUrl: './my-conference.component.html',
  styleUrls: ['./my-conference.component.css']
})
export class MyConferenceComponent implements AfterContentInit, OnInit{
  @ContentChildren(BiddingPageComponent) biddingPage: BiddingPageComponent;
  @Input("conferenceID") conferenceID;
  conference;
  today;
  constructor(private routes: ActivatedRoute,
              private  changeDetectorRef: ChangeDetectorRef,
              private http:HttpClient) {
    this.today = new Date();
    console.log(this.today);
  }

  ngOnInit(): void {
    this.http.get(`http://localhost:8081//conferences/${this.conferenceID}`).subscribe(result => {
      this.conference = result;
      console.log(this.conference);
    })
  }



  ngAfterContentInit() {

  }
}
