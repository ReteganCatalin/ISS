import {MetaInfo} from "./MetaInfo";
import {Section} from "./Section";

export class ProposalDetailed {
   proposal_id: number;
   paper_location: string;
   abstract_location: string;
   author_list: Array<string>;
   meta_info: MetaInfo;
   qualifier: Array<string>;
   section: Section;
   supervisorName: string;

  constructor() {
    this.proposal_id = 0;
    this.paper_location = "";
    this.abstract_location = "";
    this.author_list = new Array<string>();
    this.meta_info = new MetaInfo();
    this.qualifier = new Array<string>();
    this.section = new Section();
    this.supervisorName = "";
  }
}
