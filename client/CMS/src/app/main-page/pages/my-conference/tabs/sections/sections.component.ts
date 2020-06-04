import {Component, Input, OnInit} from '@angular/core';
import {HttpClient} from "@angular/common/http";

@Component({
  selector: 'sections',
  templateUrl: './sections.component.html',
  styleUrls: ['./sections.component.css']
})
export class SectionsComponent implements OnInit {
  @Input("conferenceID") conferenceID;
  pcMembers: Array<any>;
  selectedPCMember: any;
  dateOfPresentationString: string;
  priceString: string;
  sections: Array<any>;
  acceptedProposals: Array<any>;
  constructor(private http: HttpClient) {
    this.pcMembers = [];
    this.selectedPCMember = {};
    this.selectedPCMember['username'] = 'none';
    this.dateOfPresentationString = "";
    this.priceString = "";
    this.sections = [];
    this.acceptedProposals = [];
  }

  ngOnInit(): void {
    this.loadData();
  }

  getPCMembers() {
    this.http.get<any>(`http://localhost:8081/pc_members/${this.conferenceID}`).subscribe(pcMembers => {
      this.pcMembers = pcMembers;
    })
  }

  loadData() {
    this.acceptedProposals = [];
    this.http.get<any>(`http://localhost:8081/sections/conference/${this.conferenceID}`).subscribe(result => {
      this.sections = result;
      console.log(this.sections);
    });

    this.http.get<any>(`http://localhost:8081/conferences/${this.conferenceID}/accepted`).subscribe(result => {
      let acceptedProposals = result['proposalDtoList'];
      for(let i in acceptedProposals) {
        this.acceptedProposals.push(null);
        this.setAcceptedProposals(i, acceptedProposals[i]['proposalID']);
      }
    });
  }

  private setAcceptedProposals(i, proposalID) {
    this.http.get(`http://localhost:8081/proposals/${proposalID}/detailed`).subscribe(proposal => {
      this.acceptedProposals[i] = proposal;
    })
  }

  createSection() {
    let section = {};
    section['sectionID'] = 0;
    section['supervisorID'] = this.selectedPCMember['userID'];
    section['conferenceID'] = this.conferenceID;
    section['price'] = Number.parseInt(this.priceString);
    section['dateOfPresentation'] = this.dateOfPresentationString;
    this.http.post('http://localhost:8081/sections', section).subscribe((data) => {this.loadData()});
  }

  getProposalDetailed(proposalID) {
    return this.http.get<any>(`http://localhost:8081/proposals/${proposalID}/detailed`);
  }

  proposalInASection(proposalID) {
    for(let section of this.sections)
      if (this.inSectionProposals(proposalID, section) == true)
        return true;

    return false;
  }

  inSectionProposals(proposalID, section) {
    for(let proposal of section['proposals'])
      if (proposal['proposal']['proposalID'] == proposalID)
        return true;
    return false;
  }

  addProposalToSection(acceptedProposal, section) {
    let proposal_section = {};
    console.log(acceptedProposal, section);
    proposal_section['sectionID'] = section['section']['sectionID'];
    proposal_section['proposalID'] = acceptedProposal['proposal_id'];
    this.http.post('http://localhost:8081/proposalLists', proposal_section).subscribe(() => this.loadData());
  }


  removeProposal(section: any, proposal: any) {
    console.log(section, proposal);
    let sectionID = section['section']['sectionID'];
    console.log(sectionID);
    let proposalID = proposal['proposal']['proposalID'];
    console.log(proposalID);
    this.http.delete(`http://localhost:8081/proposalLists/${sectionID}/${proposalID}/${this.conferenceID}`).subscribe(() => {
      this.loadData()
    })
  }

  deleteSection(section: any) {
    console.log(section);
    this.http.delete(`http://localhost:8081/sections/${section['section']['sectionID']}`).subscribe(() => {
      this.loadData();
    })
  }
}
