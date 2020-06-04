import { Component, OnInit } from '@angular/core';
import {BehaviorSubject, Observable} from 'rxjs';
import {Conference, Conferences} from '../../../shared/models/Conference';
import {HttpClient} from '@angular/common/http';
import {ConferenceData} from "../../../shared/models/ConferenceData";
import {map} from "rxjs/operators";

@Component({
  selector: 'app-conferences',
  templateUrl: './conferences.component.html',
  styleUrls: ['./conferences.component.css']
})
export class ConferencesComponent implements OnInit {
  conferenceObserver: BehaviorSubject<Array<Conference>>;
  constructor(private http: HttpClient) {
    this.imageLocations=[];
    this.conferenceObserver = new BehaviorSubject<Array<Conference>>(new Array<Conference>());
  }
  imageLocations : Array<string>
  ngOnInit(): void {

    this.http.get<Conferences>('http://localhost:8081/conferences').subscribe(data => {
      this.conferenceObserver.next(data["conferenceDtos"]);
      for(let conference of this.conferenceObserver.getValue()){
        this.getConferenceImageLocation(conference.conferenceID).subscribe(data => {
          if(data==null||data=="") this.imageLocations.push("assets/test-img.jpg");
          else this.imageLocations.push(data);
        });
      }
    });
  }

  onClickProposals(conferenceID: number) {
  }
  getConferenceImageLocation(conferenceID: number) {

     return this.http.get<ConferenceData>('http://localhost:8081/conference_data/'+conferenceID).pipe(map((data => {

      console.log(data);
      if(data!=null)
      return data.byteFileLocation;
      else return "";
    })));
  }
}
