import { Component, OnInit } from '@angular/core';
import {HttpClient, HttpEventType, HttpRequest, HttpResponse} from "@angular/common/http";
import {MetaInfo} from "../../../shared/models/MetaInfo";
import {PaperProposal} from "../../../shared/models/PaperProposal";
import {AbstractProposal} from "../../../shared/models/AbstractProposal";
import {Proposal} from "../../../shared/models/Proposal";
import {Author} from "../../../shared/models/Author";
import {ConferenceProposal} from "../../../shared/models/ConferenceProposal";
import {UploadFileService} from "../upload-file.service";

@Component({
  selector: 'app-add-proposal',
  templateUrl: './add-proposal.component.html',
  styleUrls: ['./add-proposal.component.css']
})
export class AddProposalComponent implements OnInit {

  selectedAbstractFiles: FileList;
  selectedPaperFiles: FileList;
  abstractFileUpload: File;
  paperFileUpload: File;
  abstractProgress: { percentage: number } = { percentage: 0 };
  paperProgress: { percentage: number } = { percentage: 0 };

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

  constructor(private http: HttpClient,private uploadService: UploadFileService) {
    this.keywords = [];
    this.topics = [];
    this.authors = [];
  }

  ngOnInit(): void {
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
    this.authors.push(this.author);
    this.author = "";
  }

  deleteAuthor(i: number) {
    this.authors = this.authors.slice(0, i).concat(this.authors.slice(i + 1, this.authors.length));
  }

  public createProposal(conferenceID: number){
    ///prima data ce e in proposal
    /// dupa author list
    /// dupa conference proposal

    this.abstractFileUpload = this.selectedAbstractFiles.item(0);
    this.paperFileUpload = this.selectedPaperFiles.item(0);
    const abstractForm: FormData = new FormData();
    const paperForm: FormData = new FormData();


    let metaInfo = new MetaInfo();
    metaInfo.keywords = this.keywords.join(',');
    metaInfo.topics = this.topics.join(',');
    metaInfo.name = this.name;
    let metaInfoID = null;

    this.http.post<MetaInfo>('http://localhost:8081/meta_informations', metaInfo).subscribe(data =>{
      metaInfoID = data.metaInfoId;
      let paperID = null;
      let paper = new PaperProposal();
      paper.byteFileLocation = "";
      paper.format = this.paperFormat;
      paperForm.append("file",this.paperFileUpload);
      //const req = new HttpRequest('POST', 'http://localhost:8081/papers', paperForm);

      this.http.post<PaperProposal>(`http://localhost:8081/papers`, paperForm).subscribe(data =>{
      //this.http.request<PaperProposal>(req).subscribe(data =>{
        paperID = data.paperId;

        let abstractID = null;
        let abs = new AbstractProposal();
        abs.byteFileLocation = "";
        abs.format = this.abstractFormat;
        abstractForm.append("file",this.abstractFileUpload);
        this.http.post<AbstractProposal>(`http://localhost:8081/abstracts`, abstractForm).subscribe(data =>{
          abstractID = data.abstractID;

          let proposal = new Proposal();
          proposal.setAttributes(0, 1, paperID, metaInfoID, abstractID);
          let proposalID = null;
          this.http.post<Proposal>('http://localhost:8081/proposals', proposal).subscribe(data =>{
            proposalID = data.proposalID;

            console.log("Intra in for");
            for (let authorName of this.authors) {
              console.log(authorName);
              let asd =  new Author();
              asd.name = authorName;
              asd.proposalId = proposalID;
              console.log(asd);
              this.http.post<Proposal>('http://localhost:8081/authors', asd).subscribe(data => {
                console.log(data);
              });
            }
            console.log("Iese in for");

            let asd = new ConferenceProposal(proposalID, conferenceID);
            console.log(asd);
            this.http.post('http://localhost:8081/conference_proposal', asd).subscribe(data=> {
              console.log("asd");
            });
          });
        });
      });
    });








  }

  selectAbstractFile(event) {
    this.selectedAbstractFiles = event.target.files;
  }

  selectPaperFile(event) {
    this.selectedPaperFiles = event.target.files;
  }

  uploadAbstract() {
    this.abstractProgress.percentage = 0;

    this.uploadService.pushFileToStorage(this.abstractFileUpload).subscribe(event => {
      if (event.type === HttpEventType.UploadProgress) {
        this.abstractProgress.percentage = Math.round(100 * event.loaded / event.total);
      } else if (event instanceof HttpResponse) {
        console.log('File is completely uploaded!');
      }
    });

    this.selectedAbstractFiles = undefined;
  }

  uploadPaper() {
    this.paperProgress.percentage = 0;

    this.uploadService.pushFileToStorage(this.paperFileUpload).subscribe(event => {
      if (event.type === HttpEventType.UploadProgress) {
        this.paperProgress.percentage = Math.round(100 * event.loaded / event.total);
      } else if (event instanceof HttpResponse) {
        console.log('File is completely uploaded!');
      }
    });

    this.selectedAbstractFiles = undefined;
  }
}
