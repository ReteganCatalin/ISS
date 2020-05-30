import {Component, Input, OnInit} from '@angular/core';
import {BehaviorSubject} from "rxjs";
import {ProposalDetailed} from "../../../../../shared/models/ProposalDetailed";
import {Proposal, Proposals} from "../../../../../shared/models/Proposal";
import {HttpClient} from "@angular/common/http";
import {ConferenceComponent} from "../../../conference/conference.component";
import {User} from "../../../../../shared/models/User";

@Component({
  selector: 'bidding-page',
  templateUrl: './bidding-page.component.html',
  styleUrls: ['./bidding-page.component.css']
})
export class BiddingPageComponent implements OnInit{
  @Input("conferenceID") conferenceID;
  conferenceProposalDetailedObserver: BehaviorSubject<Array<ProposalDetailed>>;
  conferenceProposalObserver: BehaviorSubject<Array<Proposal>>;

  public isCollapsed: Array<boolean>;
  constructor(private http: HttpClient) {
    this.conferenceProposalDetailedObserver = new BehaviorSubject<Array<ProposalDetailed>>(new Array<ProposalDetailed>());
    this.conferenceProposalObserver = new BehaviorSubject<Array<Proposal>>(new Array<Proposal>());
    this.isCollapsed = [];
    console.log(this.conferenceID);
  }

  ngOnInit(): void {

    this.http.get<Proposals>('http://localhost:8081/proposals').subscribe(proposals => {
      let auxiliaryList = new Array<ProposalDetailed>();
      proposals.proposalDtoList.forEach(proposal => {
        this.http.get<ProposalDetailed>('http://localhost:8081/proposals/' + proposal.proposalID + '/detailed').subscribe(proposalDetailed =>{
          // if (proposalDetailed.section.conferenceID == this.conferenceID) {
          //
          // }
          // Trebe puse inapoi in if
          auxiliaryList.push(proposalDetailed);
          this.isCollapsed.push(false);
        });
      });
      this.conferenceProposalDetailedObserver.next(auxiliaryList);
    });
  }
}
