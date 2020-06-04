import { Component, Input, OnInit } from '@angular/core';
import {HttpClient} from "@angular/common/http";
import {DownloadService} from "../../../../../shared/services/download.service";
import {User, UserList} from "../../../../../shared/models/User";
import {PCMember} from "../../../../../shared/models/PCMember";

@Component({
  selector: 'assign-reviewer',
  templateUrl: './assign-reviewer.component.html',
  styleUrls: ['./assign-reviewer.component.css']
})
export class AssignReviewerComponent implements OnInit {
  @Input("conferenceID") conferenceID;
  conferenceProposals: Array<any>;
  pcMembers: Map<any, any>;
  qualifiers: Array<any>;

  possiblePCMember: Array<User>;

  constructor(private http: HttpClient,
              private downloadService: DownloadService) {
    this.conferenceProposals = [];
    this.pcMembers = new Map<any, any>();
    this.possiblePCMember = [];
    this.initQualifiers();
  }

  ngOnInit(): void {
    this.loadData();

    this.http.get<UserList>('http://localhost:8081/users').subscribe(userList =>{
      this.http.get<UserList>('http://localhost:8081/conferences/' + this.conferenceID + '/pc_members').subscribe(pcMemberList =>{
        for (let i = 0; i < userList.userDtoList.length; i++){
          let ok = true;
          for(let j = 0; j < pcMemberList.userDtoList.length; j++){
            if (pcMemberList.userDtoList[j].userID == userList.userDtoList[i].userID){
              ok = false;
              break;
            }
          }
          if (ok != false)
            this.possiblePCMember.push(userList.userDtoList[i]);
        }
      })
    })
  }

  loadData() {
    this.http.get<any>(`http://localhost:8081/conferences/assign/${this.conferenceID}`).subscribe(proposalJson => {
      this.conferenceProposals = proposalJson["proposals"];
      this.http.get<any>(`http://localhost:8081//conferences/${this.conferenceID}/pc_members`).subscribe(pcMembersJson =>{
        for(let pcMember of pcMembersJson["userDtoList"])
          this.pcMembers.set(pcMember['userID'], pcMember);
      })
    })
  }

  addPCMember(i: number) {
    this.http.post('http://localhost:8081/pc_members', new PCMember(this.possiblePCMember[i].userID, this.conferenceID)).subscribe(data => {
      console.log(data);
    });
  }

  private initQualifiers() {
    this.qualifiers = [];
    this.qualifiers.push({"qualifierID":0,"name":"none"});
    this.qualifiers.push({"qualifierID": 1, "name": "strong reject"});
    this.qualifiers.push({"qualifierID": 2, "name": "reject"});
    this.qualifiers.push({"qualifierID":3,"name":"weak reject"});
    this.qualifiers.push({"qualifierID":4,"name":"borderline paper"});
    this.qualifiers.push({"qualifierID":5,"name":"weak accept"});
    this.qualifiers.push({"qualifierID":6,"name":"accept"});
    this.qualifiers.push({"qualifierID":7,"name":"strong accept"});
  }

  addReviewer(proposal, userID) {
    let review = {};
    review["reviewID"] = 0;
    review["proposalID"] = proposal['proposalData']['proposalID'];
    review["qualifierID"] = 0;
    review["userID"] = userID;

    this.http.post('http://localhost:8081/reviews', review).subscribe(
      result => {this.loadData(); console.log(result)},
      error => console.log(error));
  }

  containsReviewerID(reviews, userID) {
    for (let review of reviews)
      if (review['userID'] == userID)
        return true;
    return false;
  }

  deleteReview(reviewID) {
    this.http.delete(`http://localhost:8081/reviews/${reviewID}`).subscribe(
      result => {this.loadData();},
      error => console.log(error));//todo delete this
  }

  downloadPaper(proposalID) {
    this.downloadService.downloadPaper(proposalID)
  }

  downloadAbstract(proposalID) {
    this.downloadService.downloadAbstract(proposalID)
  }
}
