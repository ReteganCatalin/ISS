import {Component, Input, OnInit} from '@angular/core';
import {BehaviorSubject} from "rxjs";
import {Proposal, Proposals} from "../../../../../shared/models/Proposal";
import {HttpClient} from "@angular/common/http";
import {UserService} from "../../../../../shared/services/user.service";

@Component({
  selector: 'review',
  templateUrl: './review.component.html',
  styleUrls: ['./review.component.css']
})
export class ReviewComponent implements OnInit {
  proposalsList: Array<any>;
  reviewerRecommendations: Array<any>;
  otherReviewersRecommendations: Array<any>;
  public isCollapsed: Array<boolean>;
  qualifiers: Array<any>;

  @Input() conferenceID: number;

  constructor(private http: HttpClient,
              private userService: UserService) {
    this.isCollapsed = [];
    this.proposalsList = [];
    this.reviewerRecommendations = [];
    this.otherReviewersRecommendations = [];
    this.initQualifiers();
  }

  ngOnInit(): void {
    this.loadData();
  }

  private loadData() {
    this.http.get<Proposals>(`http://localhost:8081//conferences/${this.conferenceID}/proposals/reviewer/${this.userService.getUserID()}`).subscribe(proposalsJson => {
      this.proposalsList = proposalsJson['proposals'];
      for(let i in proposalsJson){
        this.reviewerRecommendations.push({});
        this.otherReviewersRecommendations.push({});
      }
    });
  }

  private initQualifiers() {
    this.qualifiers = [];
    this.qualifiers.push({"qualifierID":0,"name":"none"});
    this.qualifiers.push({"qualifierID": 1, "name": "strong reject"});
    this.qualifiers.push({"qualifierID": 2, "name": "reject"});
    this.qualifiers.push({"qualifierID":3,"name":"weak reject"});
    this.qualifiers.push({"qualifierID":4,"name":"borderline paper"});
    this.qualifiers.push({"qualifierID":5,"name":"weak accept"});
    this.qualifiers.push({"qualifierID":6,"name":"accept"});
    this.qualifiers.push({"qualifierID":7,"name":"strong accept"});
  }

  addQualifier(proposal, qualifierID) {
    let review = {};
    review["reviewID"] = proposal['review']['reviewID'];
    review["proposalID"] = proposal['proposalData']['proposalID'];
    review["qualifierID"] = qualifierID;
    review["userID"] = this.userService.getUserID();
    this.http.put(`http://localhost:8081//reviews`, review).subscribe(() => this.loadData())
  }

  private setReviewerRecommandations(i, reviewID) {

  }

  collapsedChangeStatus(i: number) {
    this.isCollapsed[i] = !this.isCollapsed[i];

  }
}
