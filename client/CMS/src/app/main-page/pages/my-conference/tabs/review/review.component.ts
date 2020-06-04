import {Component, Input, OnInit} from '@angular/core';
import {BehaviorSubject} from "rxjs";
import {Proposal, Proposals} from "../../../../../shared/models/Proposal";
import {HttpClient} from "@angular/common/http";
import {UserService} from "../../../../../shared/services/user.service";
import {DownloadService} from "../../../../../shared/services/download.service";

@Component({
  selector: 'review',
  templateUrl: './review.component.html',
  styleUrls: ['./review.component.css']
})
export class ReviewComponent implements OnInit {
  proposalsList: Array<any>;
  reviewerRecommendations: Array<any>;
  otherReviewers: Array<any>;
  public isCollapsed: Array<boolean>;
  qualifiers: Array<any>;
  recommendationText: Array<any>;
  disabledTextarea: Array<Boolean>;

  @Input() conferenceID: number;

  constructor(private http: HttpClient,
              private userService: UserService,
              private downloadService: DownloadService) {
    this.isCollapsed = [];
    this.proposalsList = [];
    this.reviewerRecommendations = [];
    this.otherReviewers = [];
    this.recommendationText = [];
    this.disabledTextarea = [];
    this.initQualifiers();
  }

  ngOnInit(): void {
    this.loadData();
  }

  private loadData() {
    this.reviewerRecommendations = [];
    this.otherReviewers = [];
    this.http.get<Proposals>(`http://localhost:8081//conferences/${this.conferenceID}/proposals/reviewer/${this.userService.getUserID()}`).subscribe(proposalsJson => {
      this.proposalsList = proposalsJson['proposals'];
      for(let i in this.proposalsList){
          this.reviewerRecommendations.push(null);
          this.otherReviewers.push(null);
          this.recommendationText.push("");
          if(!this.isCollapsed[i] == false) {
            this.setReviewerRecommendations(i, this.proposalsList[i]['review']['reviewID']);
            this.setOtherReviewers(i, this.proposalsList[i]['proposalData']['proposalID'], this.userService.getUserID());
          }
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

  private setReviewerRecommendations(i, reviewID) {
    this.http.get<Array<any>>(`http://localhost:8081//reviews/${reviewID}/recommendation`).subscribe(response => {
      this.reviewerRecommendations[i] = response;
      this.recommendationText[i] = "";
      if(this.reviewerRecommendations[i] != null)
        this.recommendationText[i] = response["recommendationMessage"]
    })
  }

  private setOtherReviewers(i, proposalID, reviewerID) {
    if(this.proposalsList[i]['review']['qualifierID'] != 0)
      this.http.get<any>(`http://localhost:8081//reviews/${proposalID}/other_reviewers/${reviewerID}`).subscribe(response => {
        this.otherReviewers[i] = response;
      });
    else this.otherReviewers[i] = null;
  }

  collapsedChangeStatus(i: number, reviewID) {
    this.isCollapsed[i] = !this.isCollapsed[i];
    if(this.reviewerRecommendations[i] == null)
      this.setReviewerRecommendations(i, reviewID);
    if(this.otherReviewers[i] == null)
      this.setOtherReviewers(i, this.proposalsList[i]['review']['proposalID'], this.userService.getUserID())
  }

  addRecommendation(i, reviewerRecommendation) {
    console.log(i);
    let recommendation = {};
    recommendation['recommendationID'] = 0;
    recommendation['reviewID'] = this.proposalsList[i]['review']['reviewID'];
    recommendation['recommendationMessage'] = this.recommendationText[i];
    this.http.post("http://localhost:8081/recommendations", recommendation).subscribe(() => {
    })
  }

  updateRecommendation(i, reviewerRecommendation) {
    let recommendation = {};
    recommendation['recommendationID'] = reviewerRecommendation['recommendationID'];
    recommendation['reviewID'] = reviewerRecommendation['reviewID'];
    recommendation['recommendationMessage'] = this.recommendationText[i];
    this.http.put("http://localhost:8081/recommendations", recommendation).subscribe(() => {
    })
  }

  saveRecommendation(i: number, reviewerRecommendation) {
    this.disabledTextarea[i]=!this.disabledTextarea[i];
    if (reviewerRecommendation == null) {
      this.addRecommendation(i, reviewerRecommendation)
    }
    else {
      this.updateRecommendation(i, reviewerRecommendation);
    }
  }

  downloadPaper(proposalID) {
    this.downloadService.downloadPaper(proposalID)
  }

  downloadAbstract(proposalID) {
    this.downloadService.downloadAbstract(proposalID)
  }
}
