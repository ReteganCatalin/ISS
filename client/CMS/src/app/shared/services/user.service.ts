import { Injectable } from '@angular/core';

@Injectable({
  providedIn: 'root'
})
export class UserService {
  private userID = 3;
  constructor() { }

  public getUserID() {
    return this.userID;
  }
}
