import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';
import { ConferencesComponent } from './pages/conferences/conferences.component';
import { MainPageRoutingModule } from './main-page-routing.module';
import {AddConferenceComponent} from './custom-components/add-conference/add-conference.component';
import {FormsModule} from '@angular/forms';
import { ConferenceComponent } from './pages/conference/conference.component';
import {NgbModule} from '@ng-bootstrap/ng-bootstrap';
import { AddProposalComponent } from './custom-components/add-proposal/add-proposal.component';


@NgModule({
  declarations: [
    ConferencesComponent,
    AddConferenceComponent,
    ConferenceComponent,
    AddProposalComponent
  ],
  imports: [
    CommonModule,
    MainPageRoutingModule,
    FormsModule,
    NgbModule
  ]
})
export class MainPageModule { }
