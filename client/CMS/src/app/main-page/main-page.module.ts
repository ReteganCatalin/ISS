import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';
import { ConferencesComponent } from './pages/conferences/conferences.component';
import { MainPageRoutingModule } from './main-page-routing.module';
import {AddConferenceComponent} from './custom-components/add-conference/add-conference.component';
import {FormsModule, ReactiveFormsModule} from '@angular/forms';
import { ConferenceComponent } from './pages/conference/conference.component';
import {NgbModule} from '@ng-bootstrap/ng-bootstrap';
import { MyConferenceComponent } from './pages/my-conference/my-conference.component';
import { BiddingPageComponent } from './pages/my-conference/tabs/bidding-page/bidding-page.component';
import { AssignReviewerComponent } from './pages/my-conference/tabs/assign-reviewer/assign-reviewer.component';
import {ReviewComponent} from "./pages/my-conference/tabs/review/review.component";
import { AddProposalComponent } from './custom-components/add-proposal/add-proposal.component';
import { MyProposalsComponent } from './pages/my-proposals/my-proposals.component';
import { EditProposalComponent } from './custom-components/edit-proposal/edit-proposal.component';
import { SectionsComponent } from './pages/my-conference/tabs/sections/sections.component';


@NgModule({
  declarations: [
    ConferencesComponent,
    AddConferenceComponent,
    ConferenceComponent,
    MyProposalsComponent,
    EditProposalComponent,
    MyConferenceComponent,
    BiddingPageComponent,
    AddProposalComponent,
    AssignReviewerComponent,
    ReviewComponent,
    SectionsComponent
  ],
  exports: [
    MyConferenceComponent
  ],
  imports: [
    CommonModule,
    MainPageRoutingModule,
    FormsModule,
    NgbModule
  ]
})
export class MainPageModule { }
