import { Component, OnInit } from '@angular/core';
import {ConferenceComponent} from "../conference.component";
import {Conference} from "../../../../shared/models/Conference";

@Component({
  selector: 'app-conference-info',
  templateUrl: './conference-info.component.html',
  styleUrls: ['./conference-info.component.css']
})
export class ConferenceInfoComponent implements OnInit {

  conference: Conference;
  private parentComponent: ConferenceComponent;

  constructor(private parent: ConferenceComponent) {
    this.parentComponent = parent;
  }

  ngOnInit(): void {
    this.conference = this.parentComponent.conference;
  }

}
