import { Component, OnInit } from '@angular/core';
import {BehaviorSubject} from 'rxjs';
import {Conference, Conferences} from '../../../shared/models/Conference';
import {HttpClient} from '@angular/common/http';
import {UserList} from "../../../shared/models/User";

@Component({
  selector: 'app-conferences',
  templateUrl: './conferences.component.html',
  styleUrls: ['./conferences.component.css']
})
export class ConferencesComponent implements OnInit {
  conferenceObserver: BehaviorSubject<Array<Conference>>;
  constructor(private http: HttpClient) {
    this.conferenceObserver = new BehaviorSubject<Array<Conference>>(new Array<Conference>());
  }

  ngOnInit(): void {

    this.http.get<Conferences>('http://localhost:8081/conferences').subscribe(data => {
      this.conferenceObserver.next(data["conferenceDtos"]);
    });
  }

  onClickProposals(conferenceID: number) {
  }
}
