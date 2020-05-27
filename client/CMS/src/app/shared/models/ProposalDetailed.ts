import {MetaInfo} from "./MetaInfo";
import {Section} from "./Section";

export class ProposalDetailed {
  paper_location: string;
  proposal_id: number;
  author_list: Array<string>;
  abstract_location: string;
  qualifier: Array<string>;
  meta_info: MetaInfo;
  section: Array<Section>;
  topics: string;
  keywords: Array<string>;
  name: string;

  public setAttributes(paper_location: string, proposal_id: number, author_list: Array<string>, abstract_location: string, dateOfPresentation: Date, qualifier: Array<string>, topics: string, keywords: Array<string>, name: string, meta_info: MetaInfo, section: Array<Section>) {
    this.paper_location = paper_location;
    this.proposal_id = proposal_id;
    this.author_list = author_list;
    this.abstract_location = abstract_location;
    this.qualifier = qualifier;
    this.topics = topics;
    this.keywords = keywords;
    this.name = name;
    this.meta_info = meta_info;
    this.section = section;
  }

  constructor() {
    this.paper_location = "";
    this.proposal_id = 0;
    this.author_list = new Array<string>();
    this.abstract_location = "";
    this.qualifier = new Array<string>();
    this.topics = "";
    this.keywords = new Array<string>();
    this.name = "";
    this.meta_info = new MetaInfo();
    this.section = new Array<Section>();
  }

}
