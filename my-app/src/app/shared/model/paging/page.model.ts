export class Page<T> {

  constructor(public content: Array<T>,
              public totalPages: number,
              public last: boolean,
              public numberOfElements: number,
              public first: boolean,
              public size: number,
              public number: number) {
  }

}
