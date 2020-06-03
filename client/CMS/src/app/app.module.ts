import { BrowserModule } from '@angular/platform-browser';
import { NgModule } from '@angular/core';

import { AppRoutingModule } from './app-routing.module';
import { AppComponent } from './app.component';
import {HttpClientModule} from '@angular/common/http';
import {FormsModule, ReactiveFormsModule} from '@angular/forms';
import { ConferencesPageComponent } from './conferences-page/conferences-page.component';
import { MainPageComponent } from './main-page/main-page.component';
import {ConferenceService} from './shared/services/conference.service';
import {MainPageModule} from "./main-page/main-page.module";
import {UserService} from "./shared/services/user.service";
import {NgbCollapseModule} from "@ng-bootstrap/ng-bootstrap";
import { DashboardComponent } from './dashboard/dashboard.component';
import { LoginPageComponent } from './login-page/login-page.component';
import { RegisterPageComponent } from './register-page/register-page.component';

@NgModule({
  declarations: [
    AppComponent,
    ConferencesPageComponent,
    MainPageComponent,
    DashboardComponent,
    LoginPageComponent,
    RegisterPageComponent
  ],
  imports: [
    BrowserModule,
    AppRoutingModule,
    HttpClientModule,
    FormsModule,
    NgbCollapseModule,
    NgbCollapseModule,
    FormsModule,
    MainPageModule,
    ReactiveFormsModule
  ],
  providers: [
    ConferenceService,
    UserService
  ],
  exports: [
  ],
  bootstrap: [AppComponent]
})
export class AppModule { }
