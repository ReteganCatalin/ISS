export class Proposal {
   proposalID: number;
   userInfoID: number;
   paperID: number;
   metaInfoID: number;
   abstractID: number;

  constructor() {
    this.proposalID = 0;
    this.userInfoID = 0;
    this.paperID = 0;
    this.metaInfoID = 0;
    this.abstractID = 0;
  }

  setAttributes(proposalID: number, userInfoID: number, paperID: number, metaInfoID: number, abstractID: number) {
    this.proposalID = proposalID;
    this.userInfoID = userInfoID;
    this.paperID = paperID;
    this.metaInfoID = metaInfoID;
    this.abstractID = abstractID;
  }

  public getProposalID(): number {
    return this.proposalID;
  }
}

export class Proposals{
  proposalDtoList: Array<Proposal>;
}
