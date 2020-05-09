import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';
import { ConferencesComponent } from './pages/conferences/conferences.component';
import { MainPageRoutingModule } from './main-page-routing.module';
import {AddConferenceComponent} from './custom-components/add-conference/add-conference.component';
import {FormsModule} from '@angular/forms';

@NgModule({
  declarations: [
    ConferencesComponent,
    AddConferenceComponent
  ],
  imports: [
    CommonModule,
    MainPageRoutingModule,
    FormsModule
  ]
})
export class MainPageModule { }
