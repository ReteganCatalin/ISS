export class User {
  private _userID: number;
  private _username: string;
  private _password: string;
  private _isValidated: boolean;
  private _userInfoID: number;

  constructor() {
    this._userID = 0;
    this._username = "";
    this._password = "";
    this._isValidated = false;
    this._userInfoID = 0;
  }

  get userID(): number {
    return this._userID;
  }

  set userID(value: number) {
    this._userID = value;
  }

  get username(): string {
    return this._username;
  }

  set username(value: string) {
    this._username = value;
  }

  get password(): string {
    return this._password;
  }

  set password(value: string) {
    this._password = value;
  }

  get isValidated(): boolean {
    return this._isValidated;
  }

  set isValidated(value: boolean) {
    this._isValidated = value;
  }

  get userInfoID(): number {
    return this._userInfoID;
  }

  set userInfoID(value: number) {
    this._userInfoID = value;
  }
}
