import {Component, EventEmitter, Input, OnInit, Output} from '@angular/core';
import {ActivatedRoute} from '@angular/router';
import {saveAs} from 'file-saver/FileSaver';
import {ReviewService} from '../../core/service/review.service';
import {Review} from '../../shared/model/review/review.model';
import {DocumentService} from '../../core/service/document.service';
import {ReviewStatusService} from '../../core/service/utils/review-status.service';
import {FormBuilder, FormGroup, Validators} from '@angular/forms';
import {BriefUser} from '../../shared/model/user/brief-user.model';
import {TokenService} from '../../core/service/token.service';

@Component({
  selector: 'app-review-detail',
  templateUrl: './review-detail.component.html',
  styleUrls: ['./review-detail.component.css']
})
export class ReviewDetailComponent implements OnInit {

  form: FormGroup;
  statuses = ['Принять', 'Принять с изменениями', 'Не могу решить', 'Отклонить с изменениями', 'Отклонить'];

  constructor(private route: ActivatedRoute,
              private fb: FormBuilder,
              private tokenService: TokenService,
              private reviewService: ReviewService,
              private documentService: DocumentService,
              public reviewStatusService: ReviewStatusService) {
  }

  conferenceId: number;
  submissionId: number;
  @Input()
  reviewId: number;

  @Output()
  onDelete = new EventEmitter<any>();

  review: Review;

  ngOnInit() {
    this.reviewService.getReview(this.reviewId).subscribe(data => {
      this.review = data;
      this.createForm();
    });
  }


  downloadFile(documentId: number) {
    this.documentService.downloadFile(documentId).subscribe(
      res => {
        const contentDisposition = res.headers.get('content-disposition');
        let fileName = contentDisposition.substr(contentDisposition.indexOf('filename=') + 9);
        fileName = fileName.replace(/\"/g, '');
        console.log(fileName);
        const file: Blob = res.body;
        saveAs(file, fileName);
      });
  }

  createForm() {
    this.form = this.fb.group({
      evaluation: [{value: this.review.evaluation, disabled: true}, Validators.required],
      status: [{value: this.review.status, disabled: true}, Validators.required]
    });
  }

  changeReview() {
    this.form.controls['evaluation'].enable();
    this.form.controls['status'].enable();
  }

  saveReview() {
    this.form.controls['evaluation'].disable();
    this.form.controls['status'].disable();

    this.review.status = this.form.value.status;
    this.review.evaluation = this.form.value.evaluation;
    this.review.date = new Date();

    this.reviewService.updateReview(this.review)
      .subscribe(data => this.review = data);
  }

  submitReview() {
    this.reviewService.submitReview(this.review)
      .subscribe(data => this.review = data);
  }

  showButtons() {
    return !this.review.submitted && this.review.author.username === this.tokenService.getUsername();
  }

  canDeleteReview() {
    return !this.review.submitted;
  }

  deleteReview() {
    this.reviewService.deleteReview(this.reviewId)
      .subscribe(() => this.onDelete.emit());
  }
}
