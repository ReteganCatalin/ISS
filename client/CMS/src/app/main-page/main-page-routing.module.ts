import { NgModule } from '@angular/core';
import { Routes, RouterModule } from '@angular/router';

import {MainPageComponent} from './main-page.component';
import {ConferencesComponent} from './pages/conferences/conferences.component';
import {MyConferenceComponent} from "./pages/my-conference/my-conference.component";

const mainPageRoutes: Routes = [
  {
    path: '', component: MainPageComponent, children: [
      {path: 'conferences', component: ConferencesComponent},
      {path: 'my-conference/:id', component: MyConferenceComponent},
      {path: 'conferences/conference', loadChildren: () => import('./pages/conference/conference.module').then(m => m.ConferenceModule)},
      {path: '', redirectTo: 'conferences', pathMatch: 'full'}

      ]
  }
];

@NgModule({
  imports: [RouterModule.forChild(mainPageRoutes)],
  exports: [RouterModule]
})
export class MainPageRoutingModule { }
