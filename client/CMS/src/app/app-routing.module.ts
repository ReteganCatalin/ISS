import { NgModule } from '@angular/core';
import { Routes, RouterModule } from '@angular/router';
import {AddConferenceComponent} from './main-page/custom-components/add-conference/add-conference.component';
import {ConferencesPageComponent} from './conferences-page/conferences-page.component';
import {LoginPageComponent} from "./login-page/login-page.component";


const routes: Routes = [
  {path: 'add-conference', component: AddConferenceComponent},
  {path: 'conferences-page', component: ConferencesPageComponent},
  {path: 'main-page', loadChildren: () => import('./main-page/main-page.module').then(m => m.MainPageModule)},
  {path: 'login', component: LoginPageComponent},
  {path: '', redirectTo: 'login', pathMatch: 'full'}

  ];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule { }
