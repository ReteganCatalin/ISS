import {AfterContentInit, ChangeDetectorRef, Component, ContentChildren, Input, OnInit} from '@angular/core';
import {BiddingPageComponent} from "./tabs/bidding-page/bidding-page.component";
import { ActivatedRoute } from '@angular/router';
import {HttpClient} from "@angular/common/http";
import {UserService} from "../../../shared/services/user.service";

@Component({
  selector: 'app-my-conference',
  templateUrl: './my-conference.component.html',
  styleUrls: ['./my-conference.component.css']
})
export class MyConferenceComponent implements AfterContentInit, OnInit{
  @ContentChildren(BiddingPageComponent) biddingPage: BiddingPageComponent;
  @Input("conferenceID") conferenceID;
  conference;
  bidding_deadline;
  review_deadline;
  today;
  uid;

  constructor(private routes: ActivatedRoute,
              private  changeDetectorRef: ChangeDetectorRef,
              private http:HttpClient,
              private userService:UserService) {
    this.today = new Date();
    this.uid = userService.getUserID();
  }

  ngOnInit(): void {
    this.http.get(`http://localhost:8081//conferences/${this.conferenceID}`).subscribe(result => {
      this.conference = result;
      this.http.get(`http://localhost:8081/conferences/${this.conferenceID}/bidding`).subscribe(bidding => {
        this.bidding_deadline = new Date(bidding['deadline']);
        this.review_deadline = new Date(this.conference['reviewDeadline']);
      })
    })
  }



  ngAfterContentInit() {

  }
}
