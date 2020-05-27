import { Component, OnInit } from '@angular/core';
import {BehaviorSubject} from "rxjs";
import {ProposalDetailed} from "../../../../shared/models/ProposalDetailed";
import {Proposal, Proposals} from "../../../../shared/models/Proposal";
import {HttpClient} from "@angular/common/http";


@Component({
  selector: 'app-conference-proposals',
  templateUrl: './conference-proposals.component.html',
  styleUrls: ['./conference-proposals.component.css']
})
export class ConferenceProposalsComponent implements OnInit {
  conferenceProposalDetailedObserver: BehaviorSubject<Array<ProposalDetailed>>;
  conferenceProposalObserver: BehaviorSubject<Array<Proposal>>;
  constructor(private http: HttpClient) {
    this.conferenceProposalDetailedObserver = new BehaviorSubject<Array<ProposalDetailed>>(new Array<ProposalDetailed>());
    this.conferenceProposalObserver = new BehaviorSubject<Array<Proposal>>(new Array<Proposal>())
  }

  ngOnInit(): void {
    this.http.get<Proposals>('http://localhost:8081/proposals').subscribe(proposals => {
      let auxiliaryList = new Array<ProposalDetailed>();
      proposals.proposalDtoList.forEach(proposal => {
        this.http.get<ProposalDetailed>('http://localhost:8081/proposals/' + proposal.proposalID + '/detailed').subscribe(proposalDetailed =>{
          auxiliaryList.push(proposalDetailed);
        });
      });
      this.conferenceProposalDetailedObserver.next(auxiliaryList);
    });
  }

}
