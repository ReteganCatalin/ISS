export class Section {
   sectionID: number;
   dateOfPresentation: Date;
   supervisorID: number;
   conferenceID: number;
   price: number;

  constructor() {
    this.sectionID = 0;
    this.dateOfPresentation = new Date();
    this.supervisorID = 0;
    this.conferenceID = 0;
    this.price = 0;
  }
}
