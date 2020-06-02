import {Component, Input, OnInit} from '@angular/core';
import {BehaviorSubject} from "rxjs";
import {Proposal, Proposals} from "../shared/models/Proposal";
import {HttpClient} from "@angular/common/http";
import {ConferenceComponent} from "../main-page/pages/conference/conference.component";
import {ProposalDetailed} from "../shared/models/ProposalDetailed";
import {User} from "../shared/models/User";

@Component({
  selector: 'app-review',
  templateUrl: './review.component.html',
  styleUrls: ['./review.component.css']
})
export class ReviewComponent implements OnInit {
  conferenceProposalDetailedObserver: BehaviorSubject<Array<ProposalDetailed>>;
  conferenceProposalObserver: BehaviorSubject<Array<Proposal>>;

  @Input() conferenceID: number;

  public isCollapsed: Array<boolean>;
  constructor(private http: HttpClient) {
    this.conferenceProposalDetailedObserver = new BehaviorSubject<Array<ProposalDetailed>>(new Array<ProposalDetailed>());
    this.conferenceProposalObserver = new BehaviorSubject<Array<Proposal>>(new Array<Proposal>())
    this.conferenceID = 1;
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
          this.http.get<User>('http://localhost:8081/users/' + proposalDetailed.section.supervisorID).subscribe(data => {
            proposalDetailed.supervisorName = data.username;
            console.log(proposalDetailed.supervisorName);
          });
        });
      });
      this.conferenceProposalDetailedObserver.next(auxiliaryList);
    });
  }

}
