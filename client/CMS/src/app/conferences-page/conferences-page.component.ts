import { Component, OnInit } from '@angular/core';
import {HttpClient} from '@angular/common/http';
import {Conference, Conferences} from '../shared/models/Conference';
import {BehaviorSubject} from 'rxjs';

@Component({
  selector: 'app-conferences-page',
  templateUrl: './conferences-page.component.html',
  styleUrls: ['./conferences-page.component.css']
})
export class ConferencesPageComponent implements OnInit {
  conferenceObserver: BehaviorSubject<Array<Conference>>;
  constructor(private http: HttpClient) {
    this.conferenceObserver = new BehaviorSubject<Array<Conference>>(new Array<Conference>());
  }

  ngOnInit(): void {

    this.http.get<Conferences>('http://localhost:8081/conferences').subscribe(data => {
      this.conferenceObserver.next(data["conferenceDtos"]);
    });
  }

}
