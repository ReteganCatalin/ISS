import {Component, Input, OnInit} from '@angular/core';
import {ConferenceComponent} from "../../../conference/conference.component";
import {Conference} from "../../../../../shared/models/Conference";
import {HttpClient} from "@angular/common/http";

@Component({
  selector: 'about-conference',
  templateUrl: './about-conference.component.html',
  styleUrls: ['./about-conference.component.css']
})
export class AboutConferenceComponent implements OnInit {
  @Input("conferenceID") conferenceID;
  conference: Conference;
  constructor(private http: HttpClient) {

  }

  ngOnInit(): void {
    this.http.get<Conference>('http://localhost:8081/conferences/'+this.conferenceID).subscribe(result => {
        console.log(result);
        this.conference = result;
      }
    )
  }

}
