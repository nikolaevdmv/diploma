import {Component, EventEmitter, OnInit, Output} from '@angular/core';
import {FormBuilder, FormGroup, Validators} from '@angular/forms';
import {Review} from '../../shared/model/review/review.model';
import {ReviewStatusService} from '../../core/service/utils/review-status.service';
import {BriefUser} from '../../shared/model/user/brief-user.model';
import {TokenService} from '../../core/service/token.service';

@Component({
  selector: 'app-new-review',
  templateUrl: './new-review.component.html',
  styleUrls: ['./new-review.component.css']
})
export class NewReviewComponent implements OnInit {

  form: FormGroup;
  statuses = ['Принять', 'Принять с изменениями', 'Не могу решить', 'Отклонить с изменениями', 'Отклонить'];

  @Output()
  onSubmit = new EventEmitter<Review>();


  constructor(private fb: FormBuilder,
              private tokenService: TokenService,
              public reviewStatusService: ReviewStatusService) {
    this.createForm();
  }

  ngOnInit() {
  }

  createForm() {
    this.form = this.fb.group({
      evaluation: ['', Validators.required],
      status: ['', Validators.required]
    });
  }

  submit(review: Review) {
    review.date = new Date();
    review.author = new BriefUser(this.tokenService.getUsername());
    this.onSubmit.emit(review);
  }

}
