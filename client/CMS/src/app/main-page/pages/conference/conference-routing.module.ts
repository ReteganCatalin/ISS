import { NgModule } from '@angular/core';
import {RouterModule, Routes} from "@angular/router";
import {ConferenceComponent} from "./conference.component";
import {ConferenceProposalsComponent} from "./conference-proposals/conference-proposals.component";
import {ConferenceInfoComponent} from "./conference-info/conference-info.component";

const conferencePageRoutes: Routes = [
  {
    path: '', component: ConferenceComponent, children: [
        {path: 'proposals', component: ConferenceProposalsComponent},
        {path: 'info', component: ConferenceInfoComponent},
        {path: '', redirectTo: 'info', pathMatch: 'full'}
      ]
  }
];

@NgModule({
  imports: [RouterModule.forChild(conferencePageRoutes)],
  exports: [RouterModule]
})
export class ConferenceRoutingModule {
}
