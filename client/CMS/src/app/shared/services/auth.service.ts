import { Injectable } from '@angular/core';
import {HttpClient} from "@angular/common/http";
import {catchError, first, map} from "rxjs/operators";
import {rejects} from "assert";
import {UserList} from "../models/User";

@Injectable({
  providedIn: 'root'
})
export class AuthService {

  constructor(private http: HttpClient) { }

  logout(): void {
    console.log(sessionStorage.getItem('uid'));

    sessionStorage.removeItem('token');
    sessionStorage.removeItem('uid');
    console.log(sessionStorage.getItem('uid'));
  }

  login(username, password){
    return this.getUser(username, password).pipe(map(data => {
      if (data != null) {
        sessionStorage.setItem('uid', data.userID.toString());
        return true;
      }
      return rejects();
    }));
  }

  getUser(username, password) {
    return this.http.get<UserList>('http://localhost:8081/users').pipe(map(result => {
      for(let user of result.userDtoList){
        if (user.username == username && password == user["password"]) {
          sessionStorage.setItem('uid', user.userID.toString());
          return user;
        }
      }
      return null;
    }));

  }

  signup(username: any, password: any, firstName: any, lastName: any, email: any, phoneNumber: any, affiliation: any) {

  }
}
