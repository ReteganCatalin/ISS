import {AfterViewInit, Component, Input, OnInit} from '@angular/core';
import {HttpClient} from "@angular/common/http";
import {ProposalDetailed} from "../../../shared/models/ProposalDetailed";
import {MetaInfo} from "../../../shared/models/MetaInfo";
import {PaperProposal} from "../../../shared/models/PaperProposal";
import {AbstractProposal} from "../../../shared/models/AbstractProposal";
import {rejects} from "assert";
import {ConferenceProposal} from "../../../shared/models/ConferenceProposal";

@Component({
  selector: 'app-edit-proposal',
  templateUrl: './edit-proposal.component.html',
  styleUrls: ['./edit-proposal.component.css']
})
export class EditProposalComponent implements OnInit {

  keywords : Array<string>;
  keyword : string;
  topics : Array<string>;
  topic : string;

  name : string;

  author: string;
  authors: Array<string>;

  abstractLocation: string;
  abstractFormat: string;

  paperLocation: string;
  paperFormat: string;

  proposal: ProposalDetailed;

  loadProposalData(proposal: ProposalDetailed){
    this.proposal = proposal;
    console.log(this.proposal);
  }

  addKeyword(){
    this.keywords.push(this.keyword);
    this.keyword = "";
  }

  addTopic() {
    this.topics.push(this.topic);
    this.topic = "";
  }

  deleteTopic(i: number) {
    this.topics = this.topics.slice(0, i).concat(this.topics.slice(i + 1, this.topics.length));
  }

  deleteKeyword(i: number) {
    this.keywords = this.keywords.slice(0, i).concat(this.keywords.slice(i + 1, this.keywords.length));
  }

  changeAbstractFormat() {
    this.abstractFormat = this.abstractLocation.slice(this.abstractLocation.lastIndexOf("."), this.abstractLocation.length);
  }

  changePaperFormat() {
    console.log(this.paperLocation);
    this.paperFormat = this.paperLocation.slice(this.paperLocation.lastIndexOf("."), this.paperLocation.length);
  }

  addAuthor() {
    this.proposal.author_list.push(this.author);
    this.author = "";
  }

  deleteAuthor(i: number) {
    this.authors = this.authors.slice(0, i).concat(this.authors.slice(i + 1, this.authors.length));
  }

  public updateProposalData(){
    this.proposal.meta_info.keywords = this.keywords.join(',');
    this.proposal.meta_info.topics = this.topics.join(',');

    this.http.put<MetaInfo>('http://localhost:8081/meta_informations', this.proposal.meta_info).subscribe(() => {
      let paper = new PaperProposal();
      paper.byteFileLocation = this.proposal.paper_location;
      paper.format = this.paperFormat;
      paper.paperId = this.proposal["proposal_proper_info"]["paperID"];

      this.http.put<PaperProposal>('http://localhost:8081/papers', paper).subscribe( () => {
        let abstractProposal = new AbstractProposal();
        abstractProposal.byteFileLocation = this.proposal.abstract_location;
        abstractProposal.format = this.abstractFormat;
        abstractProposal.abstractID = this.proposal["proposal_proper_info"]["abstractID"];

        console.log(abstractProposal);
        this.http.put<AbstractProposal>('http://localhost:8081/abstracts', abstractProposal).subscribe(() => {
        });
      });
    });
  }


  constructor(private http: HttpClient) { }

  ngOnInit(): void {
    this.authors = this.proposal.author_list;
    this.topics = this.proposal.meta_info.topics.split(",");
    this.keywords = this.proposal.meta_info.keywords.split(',');

    this.abstractLocation = this.proposal.abstract_location;
    this.paperLocation = this.proposal.paper_location;
    this.changePaperFormat();
    this.changeAbstractFormat();
  }

}
