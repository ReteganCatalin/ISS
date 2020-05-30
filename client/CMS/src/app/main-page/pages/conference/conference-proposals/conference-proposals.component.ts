import {Component, Input, OnInit} from '@angular/core';
import {BehaviorSubject} from "rxjs";
import {ProposalDetailed} from "../../../../shared/models/ProposalDetailed";
import {Proposal, Proposals} from "../../../../shared/models/Proposal";
import {HttpClient} from "@angular/common/http";
import {ActivatedRoute} from "@angular/router";
import {ConferenceComponent} from "../conference.component";
import {User} from "../../../../shared/models/User";
import {newArray} from "@angular/compiler/src/util";


@Component({
  selector: 'app-conference-proposals',
  templateUrl: './conference-proposals.component.html',
  styleUrls: ['./conference-proposals.component.css']
})
export class ConferenceProposalsComponent implements OnInit {
  conferenceProposalDetailedObserver: BehaviorSubject<Array<ProposalDetailed>>;
  conferenceProposalObserver: BehaviorSubject<Array<Proposal>>;

  @Input() conferenceID: number;

  public isCollapsed: Array<boolean>;
  constructor(private http: HttpClient, private parent: ConferenceComponent) {
    this.conferenceProposalDetailedObserver = new BehaviorSubject<Array<ProposalDetailed>>(new Array<ProposalDetailed>());
    this.conferenceProposalObserver = new BehaviorSubject<Array<Proposal>>(new Array<Proposal>())
    this.conferenceID = +this.parent.conferenceID;
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
