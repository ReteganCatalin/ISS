export class Section {
  private _sectionID: number;
  private _dateOfPresentation: Date;
  private _supervisorID: number;
  private _conferenceID: number;
  private _price: number;


  get sectionID(): number {
    return this._sectionID;
  }

  set sectionID(value: number) {
    this._sectionID = value;
  }

  get dateOfPresentation(): Date {
    return this._dateOfPresentation;
  }

  set dateOfPresentation(value: Date) {
    this._dateOfPresentation = value;
  }

  get supervisorID(): number {
    return this._supervisorID;
  }

  set supervisorID(value: number) {
    this._supervisorID = value;
  }

  get conferenceID(): number {
    return this._conferenceID;
  }

  set conferenceID(value: number) {
    this._conferenceID = value;
  }

  get price(): number {
    return this._price;
  }

  set price(value: number) {
    this._price = value;
  }

  constructor() {
    this._sectionID = 0;
    this._dateOfPresentation = new Date();
    this._supervisorID = 0;
    this._conferenceID = 0;
    this._price = 0;
  }
}
