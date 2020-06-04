import { Injectable } from '@angular/core';
import {ProposalDetailed} from "../models/ProposalDetailed";
import {Proposal} from "../models/Proposal";
import {HttpClient, HttpHeaders} from "@angular/common/http";
import * as fileSaver from 'file-saver';// npm i --save file-saver

@Injectable({
  providedIn: 'root'
})
export class DownloadService {

  constructor(private http : HttpClient) { }

  private saveFile(data: any, filename?: string) {
    const blob = new Blob([data], {type: 'text/csv; charset=utf-8'});
    fileSaver.saveAs(blob, filename);
  }

  downloadPaper(proposalID) {
    this.http.get<any>(`http://localhost:8081/proposals/${proposalID}/detailed`).subscribe( proposal => {
      let headers = new HttpHeaders();
      headers = headers.append('Accept', 'image/gif');
      this.http.get(`http://localhost:8081/papers/${proposal.proposal_proper_info.paperID}/file`,{
        headers: headers,
        observe: 'response',
        responseType: 'blob'
      }).subscribe( file => {
        console.log(file);
        this.saveFile(file.body,proposal.paper_location.split("\\").reverse()[0]);
      });
    });
  }

  downloadAbstract(proposalID) {
    this.http.get<any>(`http://localhost:8081/proposals/${proposalID}/detailed`).subscribe( proposal => {
      let headers = new HttpHeaders();
      headers = headers.append('Accept', 'image/gif');
      this.http.get(`http://localhost:8081/abstracts/${proposal.proposal_proper_info.abstractID}/file`,{
        headers: headers,
        observe: 'response',
        responseType: 'blob'
      }).subscribe( file => {
        console.log(file);
        this.saveFile(file.body,proposal.abstract_location.split("\\").reverse()[0]);
      });
    });
  }
}
