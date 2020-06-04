export class ConferenceData {
  conferenceID: number;
  callForPaper: string;
  about: string;
  format: string;
  byteFileLocation: string;

  constructor() {
    this.conferenceID = 0;
    this.callForPaper = '';
    this.about = '';
    this.format = '';
    this.byteFileLocation = '';
  }

  public setAttributes(conferenceID, call_for_paper, about, format, byte_file_location) {
    this.conferenceID = conferenceID;
    this.callForPaper = call_for_paper;
    this.about = about;
    this.format = format;
    this.byteFileLocation = byte_file_location;
  }

}

export class ConferenceDatas{
  conferences: Array<ConferenceData>;
}
