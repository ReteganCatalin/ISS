import {AfterViewInit, Component, OnInit} from '@angular/core';
import {ConferenceComponent} from "../conference.component";
import {Conference} from "../../../../shared/models/Conference";
import {ConferenceData} from "../../../../shared/models/ConferenceData";
import {HttpClient} from "@angular/common/http";

@Component({
  selector: 'app-conference-info',
  templateUrl: './conference-info.component.html',
  styleUrls: ['./conference-info.component.css']
})
export class ConferenceInfoComponent implements OnInit {

  conference: Conference;
  private parentComponent: ConferenceComponent;

  callForPaper: string;
  about: string;
  byteFileLocation: string;

  constructor(private parent: ConferenceComponent, private http: HttpClient) {
    this.parentComponent = parent;
  }

  ngOnInit(): void {
    this.conference = this.parentComponent.conference;
    this.getData().then(data => {
      this.callForPaper = data.callForPaper;
      this.about = data.about;
      this.byteFileLocation = data.byteFileLocation;
    });
  }

  getData(){
    return this.http.get<ConferenceData>('http://localhost:8081/conference_data/' + this.conference.conferenceID).toPromise();
  }
}
