import {Component, EventEmitter, Input, OnInit, Output} from '@angular/core';
import {Page} from '../../shared/model/paging/page.model';
import {BriefConferenceRequest} from '../../shared/model/conference-request/brief-request.model';

@Component({
  selector: 'app-request-list-table',
  templateUrl: './request-list-table.component.html',
  styleUrls: ['./request-list-table.component.css']
})
export class RequestListTableComponent implements OnInit {

  @Input()
  page: Page<BriefConferenceRequest>;

  @Output()
  onPageChange = new EventEmitter<any>();

  constructor() { }

  ngOnInit() {
  }

  pageChange(page: number) {
    this.onPageChange.emit(page);
  }
}
