import { NgModule } from '@angular/core';
import { Routes, RouterModule } from '@angular/router';
import {AddConferenceComponent} from './main-page/custom-components/add-conference/add-conference.component';
import {ConferencesPageComponent} from './conferences-page/conferences-page.component';
import {LoginPageComponent} from "./login-page/login-page.component";
import {RegisterPageComponent} from "./register-page/register-page.component";


const routes: Routes = [
  {path: 'main-page', loadChildren: () => import('./main-page/main-page.module').then(m => m.MainPageModule)},
  {path: 'login', component: LoginPageComponent},
  {path: 'register', component: RegisterPageComponent},
  {path: '', redirectTo: 'login', pathMatch: 'full'}

  ];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule { }
