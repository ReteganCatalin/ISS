import { Injectable } from '@angular/core';

@Injectable({
  providedIn: 'root'
})
export class UserService {
  private userID = 1;
  constructor() { }

  public getUserID() {
    return this.userID;
  }

  public setUserID(userID: number){
    this.userID = userID;
    console.log(this.userID);
  }
}
