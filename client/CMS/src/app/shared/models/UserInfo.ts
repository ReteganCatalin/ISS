export class UserInfo {
  userInfoId: number;
  name: string;
  affiliation: string;
  emailAddress: string;
  webPageAddress: string;
  affiliationValidated: boolean;

  constructor() {
    this.userInfoId = 0;
    this.name = "";
    this.affiliation = "";
    this.emailAddress = "";
    this.webPageAddress = "";
    this.affiliationValidated = false;
  }
}

export class UserInfoList {
  userInfoDtoList: Array<UserInfo>;
}
