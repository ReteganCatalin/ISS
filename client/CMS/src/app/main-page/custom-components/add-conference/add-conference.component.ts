import {Component, OnInit} from '@angular/core';
import { Conference} from '../../../shared/models/Conference';
import {HttpClient} from '@angular/common/http';

@Component({
  selector: 'app-add-conference',
  templateUrl: './add-conference.component.html',
  styleUrls: ['./add-conference.component.css']
})
export class AddConferenceComponent implements OnInit {
  name: string;
  startDate: string;
  endDate: string;
  proposalDeadline: string;
  paperDeadline: string;
  constructor(private http: HttpClient) { }

  ngOnInit(): void {}

  createConference() {
    const conference = new Conference();
    console.log(this.name,
      new Date(this.startDate),
      new Date(this.endDate),
      new Date(this.proposalDeadline),
      new Date(this.paperDeadline));
    conference.setAttributes(
        0,
        this.name,
        new Date(this.startDate),
        new Date(this.endDate),
        new Date(this.proposalDeadline),
        new Date(this.paperDeadline));
    this.http.post<Conference>('http://localhost:8081/conferences', conference).subscribe(result => console.log(result));
  }

}
