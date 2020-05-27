export class Conference {
  conferenceID: number;
  name: string;
  startDate: Date;
  endDate: Date;
  proposalDeadline: Date;
  paperDeadline: Date;

  constructor() {
    this.conferenceID = 0;
    this.name = '';
    this.startDate = new Date();
    this.endDate = new Date();
    this.proposalDeadline = new Date();
    this.paperDeadline = new Date();
  }

  public setAttributes(conferenceID, name, startDate, endDate, proposalDeadline, paperDeadline) {
    this.conferenceID = conferenceID;
    this.name = name;
    this.startDate = startDate;
    this.endDate = endDate;
    this.proposalDeadline = proposalDeadline;
    this.paperDeadline = paperDeadline;
  }

  public getName() {
    return this.name;
  }
}

export class Conferences{
  conferenceDtos: Array<Conference>;
}
