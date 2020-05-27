import {Component, Input, OnInit} from '@angular/core';

@Component({
  selector: 'app-conference',
  templateUrl: './conference.component.html',
  styleUrls: ['./conference.component.css']
})
export class ConferenceComponent implements OnInit {

  @Input() conferenceID: number;

  constructor() { }

  ngOnInit(): void {
  }

}
