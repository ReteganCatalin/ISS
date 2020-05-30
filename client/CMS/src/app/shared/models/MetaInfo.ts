export class MetaInfo {
  private _keywords : string;
  private _topics : string;
  private _name : string;
  private _metaInfoId: number;


  get keywords(): string {
    return this._keywords;
  }

  set keywords(value: string) {
    this._keywords = value;
  }

  get topics(): string {
    return this._topics;
  }

  set topics(value: string) {
    this._topics = value;
  }

  get name(): string {
    return this._name;
  }

  set name(value: string) {
    this._name = value;
  }

  get metaInfoId(): number {
    return this._metaInfoId;
  }

  set metaInfoId(value: number) {
    this._metaInfoId = value;
  }

  constructor() {
    this._keywords = "";
    this._topics = "";
    this._name = "";
    this._metaInfoId = 0;
  }
}
