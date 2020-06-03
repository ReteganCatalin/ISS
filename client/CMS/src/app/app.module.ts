import { BrowserModule } from '@angular/platform-browser';
import { NgModule } from '@angular/core';

import { AppRoutingModule } from './app-routing.module';
import { AppComponent } from './app.component';
import {HttpClientModule} from '@angular/common/http';
import {FormsModule, ReactiveFormsModule} from '@angular/forms';
import { ConferencesPageComponent } from './conferences-page/conferences-page.component';
import { MainPageComponent } from './main-page/main-page.component';
import { ReviewComponent } from './review/review.component';
import {NgbCollapseModule} from "@ng-bootstrap/ng-bootstrap";
import { DashboardComponent } from './dashboard/dashboard.component';
import { LoginPageComponent } from './login-page/login-page.component';

@NgModule({
  declarations: [
    AppComponent,
    ConferencesPageComponent,
    MainPageComponent,
    ReviewComponent,
    DashboardComponent,
    LoginPageComponent
  ],
  imports: [
    BrowserModule,
    AppRoutingModule,
    HttpClientModule,
    FormsModule,
    NgbCollapseModule,
    ReactiveFormsModule
  ],
  providers: [],
  bootstrap: [AppComponent]
})
export class AppModule { }
