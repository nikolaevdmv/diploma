import {Component, HostListener, OnInit} from '@angular/core';

declare const window: any;

@Component({
  selector: 'app-side',
  templateUrl: './side.component.html',
  styleUrls: ['./side.component.css']
})
export class SideComponent implements OnInit {


  margin: number;

  constructor() {
  }

  ngOnInit() {
  }

  @HostListener('window:scroll', [])
  onWindowScroll() {

    const number = window.pageYOffset || document.documentElement.scrollTop || document.body.scrollTop || 0;
    if (number > 0 && number < 56) {
      this.margin = -number;
    }
    if (number < 56) {
      this.margin = - number;
    }
    // if (number > 56) {
    //   this.margin = undefined;
    // }
  }

  getMargin() {
    return this.margin;
  }

}
