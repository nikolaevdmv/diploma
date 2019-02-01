import {
  Component, EventEmitter, Input, OnInit, Output
} from '@angular/core';
import {BriefConference} from '../../shared/model/conference/brief-conference.model';
import {Page} from '../../shared/model/paging/page.model';

@Component({
  selector: 'app-conference-list-table',
  templateUrl: './conference-list-table.component.html',
  styleUrls: ['./conference-list-table.component.css']
})
export class ConferenceListTableComponent implements OnInit {

  @Input()
  page: Page<BriefConference>;

  @Output()
  onPageChange = new EventEmitter<any>();

  constructor() {

  }

  ngOnInit() {

  }

  pageChange(page: number) {
    this.onPageChange.emit(page);

  }


}
