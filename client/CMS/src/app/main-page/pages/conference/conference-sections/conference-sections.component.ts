import { Component, OnInit } from '@angular/core';
import {HttpClient} from "@angular/common/http";
import {ConferenceComponent} from "../conference.component";
import {UserService} from "../../../../shared/services/user.service";

@Component({
  selector: 'app-conference-sections',
  templateUrl: './conference-sections.component.html',
  styleUrls: ['./conference-sections.component.css']
})
export class ConferenceSectionsComponent implements OnInit {
  conferenceID: number;
  tickets: Array<any>;
  sections: Array<any>;
  constructor(private http:HttpClient,
              private parent: ConferenceComponent,
              private userService: UserService) {
    this.conferenceID = +this.parent.conferenceID;
  }

  ngOnInit(): void {
    this.loadData()
  }

  loadData() {
    this.http.get<any>(`http://localhost:8081/mytickets/user=${this.userService.getUserID()}`).subscribe( result =>{
      this.tickets = result['myTicketDtoList'];
      console.log(this.tickets);
      this.http.get<any>(`http://localhost:8081/sections/conference/${this.conferenceID}`).subscribe(resultSections => {
        this.sections = resultSections;
      });
    });

  }

  buyTicket(section: any) {
    let ticket = {};
    ticket['ticketID'] = 0;
    ticket['userID'] = this.userService.getUserID();
    ticket['sectionID'] = section['section']['sectionID'];
    //ticket['price'] = section['section']['price'];
    ticket['price'] = 10;
    this.http.post(`http://localhost:8081/mytickets`, ticket).subscribe(data => {this.loadData()})
  }

  hasTicket(sectionID) {
    for(let ticket of this.tickets)
      if(ticket['sectionID'] == sectionID)
        return true;
    return false
  }
}
