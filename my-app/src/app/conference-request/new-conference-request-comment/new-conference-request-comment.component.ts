import {Component, EventEmitter, OnInit, Output} from '@angular/core';
import {FormBuilder, FormGroup, Validators} from '@angular/forms';
import {ConferenceRequestComment} from '../../shared/model/conference-request/request-comment.model';

@Component({
  selector: 'app-new-conference-request-comment',
  templateUrl: './new-conference-request-comment.component.html',
  styleUrls: ['./new-conference-request-comment.component.css']
})
export class NewConferenceRequestCommentComponent implements OnInit {

  form: FormGroup;

  @Output()
  onSubmit = new EventEmitter();
  statuses = ['Принять', 'Отклонить'];

  constructor(private fb: FormBuilder) {
  }

  ngOnInit() {
    this.createForm();
  }

  createForm() {
    this.form = this.fb.group({
      status: ['', Validators.required],
      comment: ['']
    });
  }

  submit(value: any) {
    if (value.status === this.statuses[0]) {
      value.status = 2;
    } else if (value.status === this.statuses[1]) {
      value.status = 1;
    }
    const comment = new ConferenceRequestComment(new Date(), value.comment, value.status);
    this.onSubmit.emit(comment);
  }


}
