import {Component, Input, OnInit} from '@angular/core';
import {ConferenceComponent} from "../../../conference/conference.component";
import {Conference} from "../../../../../shared/models/Conference";
import {HttpClient} from "@angular/common/http";
import {UserService} from "../../../../../shared/services/user.service";
import {DownloadService} from "../../../../../shared/services/download.service";

@Component({
  selector: 'about-conference',
  templateUrl: './about-conference.component.html',
  styleUrls: ['./about-conference.component.css']
})
export class AboutConferenceComponent implements OnInit {
  @Input("conferenceID") conferenceID;
  conference: Conference;
  constructor(private http: HttpClient,
              private userService: UserService,
              private downloadService: DownloadService) {

  }

  ngOnInit(): void {
    //console.log("here");
    this.http.get<Conference>('http://localhost:8081/conferences/'+this.conferenceID).subscribe(result => {
        console.log(result);
        this.conference = result;
      }
    )
  }

}
