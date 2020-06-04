export class ConferenceData {
  conferenceID: number;
  callForPaper: string;
  about: string;
  format: string;
  byteFileLocation: string;


  constructor() {
    this.conferenceID = 0;
    this.callForPaper = "";
    this.about = "";
    this.format = "";
    this.byteFileLocation = "";
  }
}
