import { NgModule } from '@angular/core';
import { Routes, RouterModule } from '@angular/router';

import {MainPageComponent} from './main-page.component';
import {ConferencesComponent} from './pages/conferences/conferences.component';


const mainPageRoutes: Routes = [
  {
    path: '', component: MainPageComponent, children: [
      {path: 'conferences', component: ConferencesComponent},
      {path: '', redirectTo: 'conferences', pathMatch: 'full'}

      ]
  }
];

@NgModule({
  imports: [RouterModule.forChild(mainPageRoutes)],
  exports: [RouterModule]
})
export class MainPageRoutingModule { }
