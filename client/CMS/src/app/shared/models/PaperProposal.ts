export class PaperProposal {
  private _paperId: number;
  private _format: string;
  private _byteFileLocation: string;


  get paperId(): number {
    return this._paperId;
  }

  set paperId(value: number) {
    this._paperId = value;
  }

  get format(): string {
    return this._format;
  }

  set format(value: string) {
    this._format = value;
  }

  get byteFileLocation(): string {
    return this._byteFileLocation;
  }

  set byteFileLocation(value: string) {
    this._byteFileLocation = value;
  }

  constructor() {
    this._paperId = 0;
    this._format = "";
    this._byteFileLocation = "";
  }
}
