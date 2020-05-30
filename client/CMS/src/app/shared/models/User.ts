export class User {
   userID: number;
   username: string;
   password: string;
   isValidated: boolean;
   userInfoID: number;

  constructor() {
    this.userID = 0;
    this.username = "";
    this.password = "";
    this.isValidated = false;
    this.userInfoID = 0;
  }
}
