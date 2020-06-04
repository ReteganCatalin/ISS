import {
  AfterViewInit,
  ChangeDetectorRef,
  Component,
  ComponentFactoryResolver,
  Input,
  OnInit,
  ViewChild, ViewChildren,
  ViewContainerRef
} from '@angular/core';
import * as fileSaver from 'file-saver';// npm i --save file-saver
import {BehaviorSubject} from "rxjs";
import {ProposalDetailed} from "../../../../shared/models/ProposalDetailed";
import {Proposal, Proposals} from "../../../../shared/models/Proposal";
import {HttpClient,HttpHeaders, HttpResponse} from "@angular/common/http";
import {ActivatedRoute, Resolve} from "@angular/router";
import {ConferenceComponent} from "../conference.component";
import {User} from "../../../../shared/models/User";
import {newArray} from "@angular/compiler/src/util";
import {AddProposalComponent} from "../../../custom-components/add-proposal/add-proposal.component";
import {ConferenceProposal} from "../../../../shared/models/ConferenceProposal";
import {ConferenceProposalDtos} from "../../../../shared/models/ConferenceProposalDtos";
import {EditProposalComponent} from "../../../custom-components/edit-proposal/edit-proposal.component";
import {PaperProposal} from "../../../../shared/models/PaperProposal";
import {DownloadService} from "../../../../shared/services/download.service";


@Component({
  selector: 'app-conference-proposals',
  templateUrl: './conference-proposals.component.html',
  styleUrls: ['./conference-proposals.component.css']
})
export class ConferenceProposalsComponent implements OnInit, AfterViewInit {
  conferenceProposalDetailedObserver: BehaviorSubject<Array<ProposalDetailed>>;
  conferenceProposalObserver: BehaviorSubject<Array<Proposal>>;

  formAddProposal;
  formEditProposal;
  @ViewChild('AddProposalForm', { read: ViewContainerRef }) entry: ViewContainerRef;
  @ViewChild('EditProposalForm', { read: ViewContainerRef }) entry2: ViewContainerRef;

  @Input() conferenceID: number;

  public isCollapsed: Array<boolean>;
  constructor(private http: HttpClient,
              private parent: ConferenceComponent,
              private resolver: ComponentFactoryResolver,
              private changeDetectorRef: ChangeDetectorRef,
              private downloadService:DownloadService) {
    this.conferenceProposalDetailedObserver = new BehaviorSubject<Array<ProposalDetailed>>(new Array<ProposalDetailed>());
    this.conferenceProposalObserver = new BehaviorSubject<Array<Proposal>>(new Array<Proposal>());
    this.conferenceID = +this.parent.conferenceID;
    this.isCollapsed = [];
    console.log(this.conferenceID);
  }

  ngOnInit(): void {

    this.http.get<ConferenceProposalDtos>('http://localhost:8081/conference_proposal').subscribe(proposals => {
      let auxiliaryList = new Array<ProposalDetailed>();
      proposals.conferenceProposalDtos.forEach(proposal => {
        if (proposal.conferenceID == this.conferenceID) {
          this.http.get<ProposalDetailed>('http://localhost:8081/proposals/' + proposal.proposalID + '/detailed').subscribe(proposalDetailed => {
            console.log(proposalDetailed.section.conferenceID);
            auxiliaryList.push(proposalDetailed);
            this.isCollapsed.push(false);
            if (proposalDetailed.section.supervisorID)
              this.http.get<User>('http://localhost:8081/users/' + proposalDetailed.section.supervisorID).subscribe(data => {
                proposalDetailed.supervisorName = data.username;
              });
          });
        }
      });
      this.conferenceProposalDetailedObserver.next(auxiliaryList);
    });
  }

  loadData(){
    this.http.get<ConferenceProposalDtos>('http://localhost:8081/conference_proposal').subscribe(proposals => {
      let auxiliaryList = new Array<ProposalDetailed>();
      proposals.conferenceProposalDtos.forEach(proposal => {
        if (proposal.conferenceID == this.conferenceID) {
          this.http.get<ProposalDetailed>('http://localhost:8081/proposals/' + proposal.proposalID + '/detailed').subscribe(proposalDetailed => {
            console.log(proposalDetailed.section.conferenceID);
            auxiliaryList.push(proposalDetailed);
            this.isCollapsed.push(false);
            if (proposalDetailed.section.supervisorID)
              this.http.get<User>('http://localhost:8081/users/' + proposalDetailed.section.supervisorID).subscribe(data => {
                proposalDetailed.supervisorName = data.username;
              });
          });
        }
      });
      this.conferenceProposalDetailedObserver.next(auxiliaryList);
    });
  }

  ngAfterViewInit(): void {

  }

  openModal(){
    this.entry.clear();
    const formFormFactory = this.resolver.resolveComponentFactory(AddProposalComponent);
    this.formAddProposal = this.entry.createComponent(formFormFactory);
    this.changeDetectorRef.detectChanges();
  }

  createProposal() {
    this.formAddProposal.instance.createProposal(this.conferenceID);
    this.closeAddModal()
  }

  async updateProposal() {
    this.formEditProposal.instance.updateProposalData();
    this.closeEditModal();
  }

  editProposal(index: number) {
    this.formEditProposal.instance.loadProposalData(this.conferenceProposalDetailedObserver.getValue()[index])
  }

  openModalEdit(index: number) {
    this.entry2.clear();
    const formFormFactory = this.resolver.resolveComponentFactory(EditProposalComponent);
    this.formEditProposal = this.entry2.createComponent(formFormFactory);
    this.editProposal(index);
    this.changeDetectorRef.detectChanges();
  }

  closeAddModal() {
    this.formAddProposal.destroy();
  }

  closeEditModal() {
    this.formEditProposal.destroy();
  }

  deleteProposal(proposalIndex: number) {
    console.log(proposalIndex);
    this.http.delete('http://localhost:8081/proposals/' + this.conferenceProposalDetailedObserver.getValue()[proposalIndex].proposal_id).subscribe(() => {this.loadData();});
  }

  downloadPaper(proposal :ProposalDetailed) {
    this.downloadService.downloadPaper(proposal.proposal_id)
  }

  downloadAbstract(proposal :ProposalDetailed) {
    this.downloadService.downloadAbstract(proposal.proposal_id)
  }
}
