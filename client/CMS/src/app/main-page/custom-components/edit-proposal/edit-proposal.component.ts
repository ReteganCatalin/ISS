import {Component, Input, OnInit} from '@angular/core';
import {HttpClient} from "@angular/common/http";
import {ProposalDetailed} from "../../../shared/models/ProposalDetailed";

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

  }

  constructor(private http: HttpClient) { }

  ngOnInit(): void {
  }

}
