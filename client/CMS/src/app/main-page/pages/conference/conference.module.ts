import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';
import {FormsModule} from "@angular/forms";
import {NgbModule} from "@ng-bootstrap/ng-bootstrap";
import {ConferenceInfoComponent} from "./conference-info/conference-info.component";
import {ConferenceProposalsComponent} from "./conference-proposals/conference-proposals.component";
import {ConferenceRoutingModule} from "./conference-routing.module";
import { ConferenceSectionsComponent } from './conference-sections/conference-sections.component';



@NgModule({
  declarations: [
    ConferenceInfoComponent,
    ConferenceProposalsComponent,
    ConferenceSectionsComponent
  ],
  imports: [
    CommonModule,
    ConferenceRoutingModule,
    FormsModule,
    NgbModule
  ]
})
export class ConferenceModule { }
