import { NgModule } from '@angular/core';
import { Routes, RouterModule } from '@angular/router';
import {AddConferenceComponent} from './add-conference/add-conference.component';
import {ConferencesPageComponent} from './conferences-page/conferences-page.component';


const routes: Routes = [
  {path: 'add-conference', component: AddConferenceComponent},
  {path: 'conferences-page', component: ConferencesPageComponent}];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule { }
