import { BrowserModule } from '@angular/platform-browser';
import { NgModule } from '@angular/core';

import { AppRoutingModule } from './app-routing.module';
import { AppComponent } from './app.component';
import {HttpClientModule} from '@angular/common/http';
import {FormsModule} from '@angular/forms';
import { ConferencesPageComponent } from './conferences-page/conferences-page.component';
import { MainPageComponent } from './main-page/main-page.component';
import {ConferenceService} from './shared/services/conference.service';
import {MainPageModule} from "./main-page/main-page.module";

@NgModule({
  declarations: [
    AppComponent,
    ConferencesPageComponent,
    MainPageComponent
  ],
  imports: [
    BrowserModule,
    AppRoutingModule,
    HttpClientModule,
    FormsModule,
    MainPageModule
  ],
  providers: [
    ConferenceService
  ],
  bootstrap: [AppComponent]
})
export class AppModule { }
