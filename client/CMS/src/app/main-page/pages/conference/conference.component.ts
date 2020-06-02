import {
  AfterViewInit, ChangeDetectorRef,
  Component,
  ComponentFactoryResolver,
  Input,
  OnInit,
  ViewChild,
  ViewContainerRef
} from '@angular/core';
import {ActivatedRoute} from "@angular/router";
import {HttpClient} from "@angular/common/http";
import {Conference} from "../../../shared/models/Conference";
import {AddConferenceComponent} from "../../custom-components/add-conference/add-conference.component";
import {AddProposalComponent} from "../../custom-components/add-proposal/add-proposal.component";

@Component({
  selector: 'app-conference',
  templateUrl: './conference.component.html',
  styleUrls: ['./conference.component.css']
})
export class ConferenceComponent implements OnInit {

  @Input() conferenceID: number;

  conference: Conference;
  conferenceDaysUntil: number;

  constructor(private activatedRoute: ActivatedRoute, private http: HttpClient) {
      this.conferenceID = +this.activatedRoute.snapshot.paramMap.get("conferenceID");
      console.log(this.conferenceID);
  }


  ngOnInit(): void {
    this.http.get<Conference>('http://localhost:8081/conferences/' + this.conferenceID).subscribe(conference => {
      console.log(conference);
      this.conference = conference;
      this.conference.startDate = new Date(conference.startDate);
      this.conference.endDate = new Date(conference.endDate);
      this.conference.paperDeadline = new Date(conference.paperDeadline);
      this.conference.proposalDeadline = new Date(conference.proposalDeadline);

      this.conferenceDaysUntil = this.computeDate(new Date(conference.startDate));
    });
  }

  computeDate(startDate) {
    let date1 = new Date();
    const diff = startDate.getTime() - date1.getTime();
    const diffDays = Math.ceil(diff / (1000 * 3600 * 24));
    return diffDays;
  }
}
