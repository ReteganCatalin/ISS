import { Component, OnInit } from '@angular/core';
import {HttpClient} from "@angular/common/http";

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

  constructor(private http: HttpClient) { }

  ngOnInit(): void {
  }

}
