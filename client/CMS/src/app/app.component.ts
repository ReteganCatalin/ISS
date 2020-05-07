import { Component } from '@angular/core';
import {HttpClient} from '@angular/common/http';

@Component({
  selector: 'app-root',
  templateUrl: './app.component.html',
  styleUrls: ['./app.component.css']
})
export class AppComponent {
  title = 'CMS';
  constructor(private http: HttpClient) {
    http.get('http://localhost:8081/user_info/1').subscribe(data => console.log(data));
  }
}
