export class Author {
   name: string;
   proposalId: number;
   authorId: number;


  constructor(name: string, proposalId: number, authorId: number) {
    this.name = name;
    this.proposalId = proposalId;
    this.authorId = authorId;
  }
}
