import {Component, Input, OnInit} from '@angular/core';

import {ActivatedRoute, Router} from '@angular/router';
import {FormBuilder, FormGroup, Validators} from '@angular/forms';
import {SubmissionService} from '../../core/service/submission.service';
import {UserService} from '../../core/service/user.service';
import {Submission} from '../../shared/model/submission/submission.model';
import {TokenService} from '../../core/service/token.service';
import {ConferenceService} from '../../core/service/conference.service';
import {BriefUser} from '../../shared/model/user/brief-user.model';
import {SUBMISSION_STATUS} from '../../shared/model/submission/submission-status.model';


@Component({
  selector: 'app-new-submission',
  templateUrl: './new-submission.component.html',
  styleUrls: ['./new-submission.component.css']
})
export class NewSubmissionComponent implements OnInit {

  form: FormGroup;


  currentFileUpload: File;

  conferenceId: number;

  constructor(private fb: FormBuilder,
              private conferenceService: ConferenceService,
              private router: Router,
              private route: ActivatedRoute,
              private tokenService: TokenService) {
    this.createForm();
  }

  ngOnInit() {
    this.route.params.subscribe(params => {
      if (params['conferenceId']) {
        this.conferenceId = params['conferenceId'];
      }
    });
  }

  createForm() {
    this.form = this.fb.group({
      title: ['', Validators.required],
      file: []
    });
  }

  onFileChange(event) {
    if (event.target.files.length > 0) {
      const file = event.target.files[0];
      // this.form.get('file').setValue(file);
      this.currentFileUpload = file;
    }
  }


  submit(submission: Submission) {
    submission.status = SUBMISSION_STATUS.get('PENDING');
    submission.reviewable = false;
    submission.author = new BriefUser(this.tokenService.getUsername());
    // submission.author.username = this.tokenService.getUsername();
    this.conferenceService.createSubmission(submission, this.conferenceId, this.currentFileUpload)
      .subscribe(() => this.router.navigate(['/conferences', this.conferenceId, 'submissions']));
  }
}
