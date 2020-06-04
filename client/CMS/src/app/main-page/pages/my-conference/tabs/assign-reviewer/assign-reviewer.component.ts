import { Component, Input, OnInit } from '@angular/core';
import {HttpClient} from "@angular/common/http";
import {DownloadService} from "../../../../../shared/services/download.service";

@Component({
  selector: 'assign-reviewer',
  templateUrl: './assign-reviewer.component.html',
  styleUrls: ['./assign-reviewer.component.css']
})
export class AssignReviewerComponent implements OnInit {
  @Input("conferenceID") conferenceID;
  conferenceProposals: Array<any>;
  pcMembers: Map<any, any>;

  constructor(private http: HttpClient,
              private downloadService: DownloadService) {
    this.conferenceProposals = [];
    this.pcMembers = new Map<any, any>();
  }

  ngOnInit(): void {
    this.loadData();
  }

  loadData() {
    this.http.get<any>(`http://localhost:8081/conferences/assign/${this.conferenceID}`).subscribe(proposalJson => {
      this.conferenceProposals = proposalJson["proposals"];
      this.http.get<any>(`http://localhost:8081//conferences/${this.conferenceID}/pc_members`).subscribe(pcMembersJson =>{
        for(let pcMember of pcMembersJson["userDtoList"])
          this.pcMembers.set(pcMember['userID'], pcMember);
      })
    })
  }

  addReviewer(proposal, userID) {
    let review = {};
    review["reviewID"] = 0;
    review["proposalID"] = proposal['proposalData']['proposalID'];
    review["qualifierID"] = 0;
    review["userID"] = userID;

    this.http.post('http://localhost:8081/reviews', review).subscribe(
      result => {this.loadData(); console.log(result)},
      error => console.log(error));
  }

  containsReviewerID(reviews, userID) {
    for (let review of reviews)
      if (review['userID'] == userID)
        return true;
    return false;
  }

  deleteReview(reviewID) {
    this.http.delete(`http://localhost:8081/reviews/${reviewID}`).subscribe(
      result => {this.loadData();},
      error => console.log(error));//todo delete this
  }

  downloadPaper(proposalID) {
    this.downloadService.downloadPaper(proposalID)
  }

  downloadAbstract(proposalID) {
    this.downloadService.downloadAbstract(proposalID)
  }
}
