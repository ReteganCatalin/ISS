import { Component } from '@angular/core';
import {HttpClient} from '@angular/common/http';
import {Router } from "@angular/router";

@Component({
  selector: 'app-root',
  templateUrl: './app.component.html',
  styleUrls: ['./app.component.css']
})
export class AppComponent {
  title = 'CMS';
  constructor(private http: HttpClient, private route: Router) {
    console.log(sessionStorage.getItem('uid'));
    if(sessionStorage.getItem('uid') == null) {
      this.route.navigateByUrl('/login');
    }
    else {
      this.route.navigateByUrl('/main-page');
    }
  }
}
