import { Component, OnInit } from '@angular/core';
import {HttpClient} from "@angular/common/http";

@Component({
  selector: 'app-add-proposal',
  templateUrl: './add-proposal.component.html',
  styleUrls: ['./add-proposal.component.css']
})
export class AddProposalComponent implements OnInit {

  keywords : string;
  topics : string;
  name : string;

  abstractLocation: string;
  abstractFormat: string;

  paperLocation: string;
  paperFormat: string;

  constructor(private http: HttpClient) { }

  ngOnInit(): void {
  }

}
