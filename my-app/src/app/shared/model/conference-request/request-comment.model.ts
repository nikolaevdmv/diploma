
export class ConferenceRequestComment {
  constructor(public date: Date,
              public content: string,
              public status: number,
              public id?: number) {
  }
}
