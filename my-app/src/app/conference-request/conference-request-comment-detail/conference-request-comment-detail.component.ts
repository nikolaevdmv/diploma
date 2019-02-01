import {Component, Input, OnInit} from '@angular/core';
import {ConferenceRequestComment} from '../../shared/model/conference-request/request-comment.model';

@Component({
  selector: 'app-conference-request-comment-detail',
  templateUrl: './conference-request-comment-detail.component.html',
  styleUrls: ['./conference-request-comment-detail.component.css']
})
export class ConferenceRequestCommentDetailComponent implements OnInit {

  @Input()
  comment: ConferenceRequestComment;

  statuses: Map<number, string> = new Map([
    [1, 'Отклонить'],
    [2, 'Принять']
  ]);


  constructor() {
  }

  ngOnInit() {
  }

}
