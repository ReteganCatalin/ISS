export class AbstractProposal {
  private _abstractID: number;
  private _format: string;
  private _byteFileLocation: string;

  get abstractID(): number {
    return this._abstractID;
  }

  set abstractID(value: number) {
    this._abstractID = value;
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
    this._abstractID = 0;
    this._format = "";
    this._byteFileLocation = "";
  }
}
