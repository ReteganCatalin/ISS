import { Component, OnInit } from '@angular/core';
import {HttpClient} from "@angular/common/http";

import {ProposalDetailed} from "../../../shared/models/ProposalDetailed";
import {User} from "../../../shared/models/User";
import {BehaviorSubject} from "rxjs";
import {Proposal} from "../../../shared/models/Proposal";
import {Conference} from "../../../shared/models/Conference";
import {ConferenceProposalDtos} from "../../../shared/models/ConferenceProposalDtos";
import {DownloadService} from "../../../shared/services/download.service";
import {UserService} from "../../../shared/services/user.service";

@Component({
  selector: 'app-my-proposals',
  templateUrl: './my-proposals.component.html',
  styleUrls: ['./my-proposals.component.css']
})
export class MyProposalsComponent implements OnInit {

  conferenceProposalDetailedObserver: BehaviorSubject<Array<ProposalDetailed>>;
  conferenceProposalObserver: BehaviorSubject<Array<Proposal>>;

  public isCollapsed: Array<boolean>;
  private readonly uid: number;

  constructor(private http: HttpClient,
              private downloadService: DownloadService,
              private userService: UserService) {
    this.isCollapsed = [];
    this.conferenceProposalDetailedObserver = new BehaviorSubject<Array<ProposalDetailed>>(new Array<ProposalDetailed>());
    this.conferenceProposalObserver = new BehaviorSubject<Array<Proposal>>(new Array<Proposal>());
    this.uid = this.userService.getUserID();
    console.log(this.uid);
  }

  loadData(){
    this.http.get<ConferenceProposalDtos>('http://localhost:8081/conference_proposal').subscribe(proposals => {
      let auxiliaryList = new Array<ProposalDetailed>();
      proposals.conferenceProposalDtos.forEach(proposal => {
          this.http.get<ProposalDetailed>('http://localhost:8081/proposals/' + proposal.proposalID + '/detailed').subscribe(proposalDetailed => {
            console.log(proposalDetailed.section.conferenceID);
            if (proposalDetailed['proposal_proper_info']['userInfoID'] == this.uid){
              auxiliaryList.push(proposalDetailed);
              this.isCollapsed.push(false);
              if (proposalDetailed.section.supervisorID)
                this.http.get<User>('http://localhost:8081/users/' + proposalDetailed.section.supervisorID).subscribe(data => {
                  proposalDetailed.supervisorName = data.username;
                });
            }
          });
      });
      this.conferenceProposalDetailedObserver.next(auxiliaryList);
    });
  }

  ngOnInit(): void {
    this.loadData();
  }

  downloadPaper(proposal :ProposalDetailed) {
    this.downloadService.downloadPaper(proposal.proposal_id)
  }

  downloadAbstract(proposal :ProposalDetailed) {
    this.downloadService.downloadAbstract(proposal.proposal_id)
  }

}
