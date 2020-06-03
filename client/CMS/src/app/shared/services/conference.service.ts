import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import {Conferences} from "../models/Conference";
import {map} from "rxjs/operators";

@Injectable({
  providedIn: 'root'
})
export class ConferenceService {
  private conferenceURL: string;
  constructor(private http: HttpClient) {
    this.conferenceURL = "http://localhost:8081/conferences"
  }

  public getConferenceNamesWithIDs() {
    return this.getAllConferences()
      .pipe(
        map( response => {
          let conferences = [];
          for (let conference of response["conferenceDtos"]) {
            let newConference = {};
            newConference["conferenceID"] = conference["conferenceID"];
            newConference["name"] = conference["name"];
            conferences.push(newConference);
          }
          return conferences;
        })
      )
  }

  public getAllConferences() {
    return this.http.get<any>(this.conferenceURL);
  }
}
