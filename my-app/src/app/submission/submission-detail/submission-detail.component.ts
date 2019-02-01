import {Component, OnInit} from '@angular/core';
import {ActivatedRoute} from '@angular/router';
import {Location} from '@angular/common';

import {saveAs} from 'file-saver/FileSaver';
import {Submission} from '../../shared/model/submission/submission.model';
import {FormBuilder, FormGroup} from '@angular/forms';
import {User} from '../../shared/model/user/user.model';
import {SubmissionService} from '../../core/service/submission.service';
import {ConferenceService} from '../../core/service/conference.service';
import {UserService} from '../../core/service/user.service';
import {DocumentService} from '../../core/service/document.service';
import {SubmissionStatusService} from '../../core/service/utils/submission-status.service';
import {TokenService} from '../../core/service/token.service';
import {MyDocument} from '../../shared/model/document/my-document.model';
import {BriefUserRoles} from '../../shared/model/user/brief-user-roles.model';
import {ConferenceRoleService} from '../../core/service/utils/conference-role.service';

@Component({
  selector: 'app-submission-detail',
  templateUrl: './submission-detail.component.html',
  styleUrls: ['./submission-detail.component.css']
})
export class SubmissionDetailComponent implements OnInit {

  statuses: Map<number, string> = new Map([
    [0, 'В ожидании'],
    [1, 'Отклонен'],
    [2, 'Принят']
  ]);

  userRoles: BriefUserRoles;

  conferenceId: number;
  submissionId: number;
  submission: Submission;
  form: FormGroup;
  reviewers: Array<User>;

  currentFileUpload: File;

  constructor(private fb: FormBuilder,
              private route: ActivatedRoute,
              private submissionService: SubmissionService,
              private conferenceService: ConferenceService,
              private userService: UserService,
              private documentService: DocumentService,
              private location: Location,
              private tokenService: TokenService,
              private conferenceRoleService: ConferenceRoleService,
              public submissionStatusService: SubmissionStatusService) {
    this.createForm();
  }

  createForm() {
    this.form = this.fb.group({
      search: [''],
    });
  }

  ngOnInit() {
    this.route.data.subscribe(data => {
      this.submission = data.submission;
      this.conferenceService.getUserRoles(this.submission.conferenceId).subscribe(value => this.userRoles = value);
      console.log(this.submission);
    });
    this.route.params.subscribe(params => {
        if (params['submissionId']) {
          this.conferenceId = params['conferenceId'];
          this.submissionId = params['submissionId'];
        }
      }
    );
  }

  sendOnReview() {
    this.submission.reviewable = true;
    this.submissionService.setReviewable(this.submissionId, this.submission.reviewable)
      .subscribe(data => this.submission = data);
  }

  //
  // downloadFile(documentId: number) {
  //   this.documentService.downloadFile(this.submissionId, this.conferenceId, documentId).subscribe(
  //     res => {
  //       const contentDisposition = res.headers.get('content-disposition');
  //       let fileName = contentDisposition.substr(contentDisposition.indexOf('filename=') + 9);
  //       fileName = fileName.replace(/\"/g, '');
  //       console.log(fileName);
  //       const file: Blob = res.body;
  //       saveAs(file, fileName);
  //     });
  // }

  onFileChange(event) {
    if (event.target.files.length > 0) {
      const file = event.target.files[0];
      // this.form.get('file').setValue(file);
      this.currentFileUpload = file;
    }
  }

  uploadFile() {
    this.submissionService.uploadDocument(this.submissionId, this.currentFileUpload)
      .subscribe((data) => this.submission = data);
  }

  canUploadNewFile() {
    return (this.submission.status === this.submissionStatusService.getDeclinedNumber() || this.submission.documents.length === 0) &&
      this.submission.author.username === this.tokenService.getUsername();
  }

  goBack(): void {
    this.location.back();
  }

  // canSendOnReview() {
  //   if (this.userRoles !== undefined) {
  //     const set = new Set(this.userRoles.roles);
  //     return (!this.submission.reviewable &&
  //       this.submission.author.username === this.tokenService.getUsername() &&
  //       this.submission.documents.length > 0) ||
  //       (set.has(this.conferenceRoleService.getAdminNumber()) || set.has(this.conferenceRoleService.getCreatorNumber()));
  //   }
  //   return false;
  // }
  canSendOnReview() {
    if (this.userRoles !== undefined) {
      const set = new Set(this.userRoles.roles);
      return !this.submission.reviewable &&
        this.submission.documents.length > 0 &&
        (set.has(this.conferenceRoleService.getAdminNumber()) || set.has(this.conferenceRoleService.getCreatorNumber()));
    }
    return false;
  }

  deleteDocument(documentId: number) {
    this.documentService.deleteDocument(documentId)
      .subscribe(() => {
        this.submissionService.getSubmission(this.submissionId).subscribe(data => this.submission = data);
      });
  }

  canDeleteDocument(document: MyDocument) {
    if (this.submission.author.username === this.tokenService.getUsername() &&
      document.status === this.submissionStatusService.getPendingNumber() && !this.submission.reviewable) {
      return true;
    }
  }
}
