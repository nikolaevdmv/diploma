import {Component, EventEmitter, Input, OnInit, Output} from '@angular/core';
import {BriefUser} from '../../shared/model/user/brief-user.model';
import {Page} from '../../shared/model/paging/page.model';

@Component({
  selector: 'app-add-new-reviewer-table',
  templateUrl: './add-new-reviewer-table.component.html',
  styleUrls: ['./add-new-reviewer-table.component.css']
})
export class AddNewReviewerTableComponent implements OnInit {

  @Input()
  page: Page<BriefUser>;

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
