import {MetaInfo} from "./MetaInfo";
import {Section} from "./Section";

export class ProposalDetailed {
  private _proposal_id: number;
  private _paper_location: string;
  private _abstract_location: string;
  private _author_list: Array<string>;
  private _meta_info: MetaInfo;
  private _qualifier: Array<string>;
  private _section: Section;
  private _supervisorName: string;


  get supervisorName(): string {
    return this._supervisorName;
  }

  set supervisorName(value: string) {
    this._supervisorName = value;
  }

  get proposal_id(): number {
    return this._proposal_id;
  }

  get paper_location(): string {
    return this._paper_location;
  }

  get abstract_location(): string {
    return this._abstract_location;
  }

  get author_list(): Array<string> {
    return this._author_list;
  }

  get meta_info(): MetaInfo {
    return this._meta_info;
  }

  get section(): Section {
    return this._section;
  }

  set section(value: Section) {
    this._section = value;
  }

  get qualifier(): Array<string> {
    return this._qualifier;
  }


  set proposal_id(value: number) {
    this._proposal_id = value;
  }

  set paper_location(value: string) {
    this._paper_location = value;
  }

  set abstract_location(value: string) {
    this._abstract_location = value;
  }

  set author_list(value: Array<string>) {
    this._author_list = value;
  }

  set meta_info(value: MetaInfo) {
    this._meta_info = value;
  }

  set qualifier(value: Array<string>) {
    this._qualifier = value;
  }


  constructor() {
    this._proposal_id = 0;
    this._paper_location = "";
    this._abstract_location = "";
    this._author_list = new Array<string>();
    this._meta_info = new MetaInfo();
    this._qualifier = new Array<string>();
    this._section = new Section();
    this.supervisorName = "";
  }
}
