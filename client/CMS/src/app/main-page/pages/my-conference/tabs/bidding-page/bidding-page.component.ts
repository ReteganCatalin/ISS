import {Component, Input, OnInit} from '@angular/core';
import {HttpClient} from "@angular/common/http";
import {UserService} from "../../../../../shared/services/user.service";

@Component({
  selector: 'bidding-page',
  templateUrl: './bidding-page.component.html',
  styleUrls: ['./bidding-page.component.css']
})
export class BiddingPageComponent implements OnInit{
  @Input("conferenceID") conferenceID;
  conferenceProposals: Array<any>;
  biddingProcess;

  constructor(private http: HttpClient,
              private userService: UserService) {
    this.conferenceProposals = [];
  }

  ngOnInit(): void {
    this.loadData();
  }

  loadData() {
    this.http.get<any>(`http://localhost:8081/conferences/detail/${this.conferenceID}/${this.userService.getUserID()}`).subscribe(proposalJson => {
      this.biddingProcess = proposalJson["bidding_process"];
      this.conferenceProposals = proposalJson["proposals"];
    })
  }

  addAnalysis(proposal, briefAnalysis) {
    let analysisJson = {};
    analysisJson["bidID"] = this.biddingProcess["bidID"];
    analysisJson["userID"] = this.userService.getUserID();
    analysisJson["proposalID"] = proposal["proposalData"]["proposalID"];
    analysisJson["briefAnalysis"] = briefAnalysis;
    analysisJson["refuse"] = briefAnalysis === "Refuse to review";

    if (proposal['analysis']['briefAnalysis'] == null) {
      console.log(analysisJson);
      this.http.post(`http://localhost:8081/analyses`, analysisJson).subscribe(data => {
        this.loadData();
      })
    }
    else {
      this.http.put(`http://localhost:8081/analyses`, analysisJson).subscribe(data => {
        this.loadData();
      })
    }
  }

  textStyle(briefAnalysis) {
    console.log(briefAnalysis);
    return "{'text-success'}"
  }
}
