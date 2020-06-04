export class ConferenceData {
  conferenceID: number;
  call_for_paper: string;
  about: string;
  format: string;
  byte_file_location: string;

  constructor() {
    this.conferenceID = 0;
    this.call_for_paper = '';
    this.about = '';
    this.format = '';
    this.byte_file_location = '';
  }

  public setAttributes(conferenceID, call_for_paper, about, format, byte_file_location) {
    this.conferenceID = conferenceID;
    this.call_for_paper = call_for_paper;
    this.about = about;
    this.format = format;
    this.byte_file_location = byte_file_location;
  }

}

export class ConferenceDatas{
  conferences: Array<ConferenceData>;
}
