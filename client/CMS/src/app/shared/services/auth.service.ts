import { Injectable } from '@angular/core';
import {HttpClient} from "@angular/common/http";
import {catchError, first, map} from "rxjs/operators";
import {User, UserList} from "../models/User";
import {UserInfo} from "../models/UserInfo";
import {rejects} from "assert";

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
      return Promise.reject();
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

  signup(username: any, password: any, firstName: any, lastName: any, email: any, phoneNumber: any, affiliation: any, webPage: any) {
    return this.addUser(username, password, firstName, lastName, email, phoneNumber, affiliation, webPage).pipe(map(data => {
      if (data != null) {
        data.subscribe(createdUser =>{
          sessionStorage.setItem('uid', createdUser.toString());
        });
        return true;
      }
      return Promise.reject();
    }));
  }

  addUser(username: any, password: any, firstName: any, lastName: any, email: any, phoneNumber: any, affiliation: any, webPage: any){
    /// post user info
    ///post user after retrive id
    let userInfo = new UserInfo();
    userInfo.affiliation = affiliation;
    userInfo.emailAddress = email;
    userInfo.webPageAddress = webPage;
    userInfo.name = firstName + ' ' + lastName;

    return this.http.post<UserInfo>('http://localhost:8081/user_info', userInfo).pipe(map(result => {
      let user = new User();
      user.userInfoID = result.userInfoId;
      user.username = username;
      user.password = password;

      return this.http.post<User>('http://localhost:8081/users', user).pipe(resultUser => {
        console.log(resultUser);
        return resultUser;
      });
    }));
  }
}
