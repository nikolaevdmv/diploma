import {Component, Input, OnInit} from '@angular/core';
import {ActivatedRoute} from '@angular/router';
import {DocumentService} from '../../core/service/document.service';
import {MyDocument} from '../../shared/model/document/my-document.model';
import {SubmissionStatusService} from '../../core/service/utils/submission-status.service';
import {ReviewService} from '../../core/service/review.service';
import {Review} from '../../shared/model/review/review.model';
import {TokenService} from '../../core/service/token.service';
import {Location} from '@angular/common';
import {Submission} from '../../shared/model/submission/submission.model';
import {SubmissionService} from '../../core/service/submission.service';
import {saveAs} from 'file-saver/FileSaver';

@Component({
  selector: 'app-document-detail',
  templateUrl: './document-detail.component.html',
  styleUrls: ['./document-detail.component.css']
})
export class DocumentDetailComponent implements OnInit {

  documentId: number;
  document: MyDocument;
  submission: Submission;

  canCreateNewReview: boolean;

  constructor(private route: ActivatedRoute,
              public tokenService: TokenService,
              private documentService: DocumentService,
              private submissionService: SubmissionService,
              private reviewService: ReviewService,
              private location: Location,
              public submissionStatusService: SubmissionStatusService) {
  }

  ngOnInit() {
    this.route.params.subscribe(params => {
        if (params['documentId']) {
          this.documentId = params['documentId'];
          this.updateDocumentData();
        }
      }
    );
  }

  onReviewCreater(value: Review) {
    this.reviewService.createReview(this.documentId, value)
      .subscribe(() => this.updateDocumentData());
  }

  canMakeNewReview() {
    if (this.submission !== undefined && !this.submission.reviewable) {
      return false;
    }

    if (this.submission !== undefined) {
      this.submission.reviewers.forEach(value => {
        if (value.username === this.tokenService.getUsername()) {
          this.canCreateNewReview = true;
        }
      });
    }

    this.document.reviews.forEach(value => {
      if (value.author.username === this.tokenService.getUsername()) {
        this.canCreateNewReview = false;
      }
    });

    return this.canCreateNewReview;
  }

  updateDocumentData() {
    this.documentService.getDocument(this.documentId)
      .map(data => {
        this.document = data;
      }).mergeMap(() => this.submissionService.getSubmission(this.document.submissionId))
      .subscribe(value => this.submission = value);
  }

  goBack(): void {
    this.location.back();
  }

  onReviewDeleted($event: any) {
    this.updateDocumentData();
  }

  downloadFile() {
    this.documentService.downloadFile(this.documentId).subscribe(
      res => {
        const contentDisposition = res.headers.get('content-disposition');
        let fileName = contentDisposition.substr(contentDisposition.indexOf('filename=') + 9);
        fileName = fileName.replace(/\"/g, '');
        console.log(fileName);
        const file: Blob = res.body;
        saveAs(file, fileName);
      });
  }

  canSeeReviews() {
    if (this.document !== undefined && this.submission !== undefined) {
      if (this.tokenService.getUsername() === this.submission.author.username) {
        return this.document.reviews.length > 0 && this.document.status !== this.submissionStatusService.getPendingNumber();
      }
      return this.document.reviews.length > 0; // при условии, что эту страницу видят только автор, админы и рецензенты
    }
  }
}
