import { BrowserModule } from '@angular/platform-browser';
import { NgModule } from '@angular/core';

import { AppRoutingModule } from './app-routing.module';
import { AppComponent } from './app.component';
import {HttpClientModule} from '@angular/common/http';
import { AddConferenceComponent } from './add-conference/add-conference.component';
import {FormsModule} from '@angular/forms';
import { ConferencesPageComponent } from './conferences-page/conferences-page.component';

@NgModule({
  declarations: [
    AppComponent,
    AddConferenceComponent,
    ConferencesPageComponent
  ],
  imports: [
    BrowserModule,
    AppRoutingModule,
    HttpClientModule,
    FormsModule
  ],
  providers: [],
  bootstrap: [AppComponent]
})
export class AppModule { }
