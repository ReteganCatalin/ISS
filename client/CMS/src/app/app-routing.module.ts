import { NgModule } from '@angular/core';
import { Routes, RouterModule } from '@angular/router';
import {AddConferenceComponent} from './main-page/custom-components/add-conference/add-conference.component';
import {ConferencesPageComponent} from './conferences-page/conferences-page.component';
import {ReviewComponent} from "./review/review.component";


const routes: Routes = [
  {path: 'add-conference', component: AddConferenceComponent},
  {path: 'conferences-page', component: ConferencesPageComponent},
  {path: 'review', component: ReviewComponent},
  {path: 'main-page', loadChildren: () => import('./main-page/main-page.module').then(m => m.MainPageModule)},
  {path: '', redirectTo: 'main-page', pathMatch: 'full'}

  ];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule { }
