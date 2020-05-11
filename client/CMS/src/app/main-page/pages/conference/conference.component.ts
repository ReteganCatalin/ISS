import { Component, OnInit } from '@angular/core';

@Component({
  selector: 'app-conference',
  templateUrl: './conference.component.html',
  styleUrls: ['./conference.component.css']
})
export class ConferenceComponent implements OnInit {
  isCollapsedConferenceInfo = true;

  constructor() { }

  ngOnInit(): void {
  }

}
