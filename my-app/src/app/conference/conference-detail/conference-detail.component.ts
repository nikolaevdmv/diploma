import {Component, OnInit} from '@angular/core';
import {ActivatedRoute} from '@angular/router';
import {FormBuilder, FormGroup} from '@angular/forms';
import {Conference} from '../../shared/model/conference/conference.model';
import {Submission} from '../../shared/model/submission/submission.model';
import {User} from '../../shared/model/user/user.model';
import {UserService} from '../../core/service/user.service';
import {ConferenceService} from '../../core/service/conference.service';
import {BriefSubmission} from '../../shared/model/submission/brief-submission.model';
import {BriefUser} from '../../shared/model/user/brief-user.model';
import {BriefUserRoles} from '../../shared/model/user/brief-user-roles.model';
import * as _ from 'underscore';
import {Location} from '@angular/common';
@Component({
  selector: 'app-conference-detail',
  templateUrl: './conference-detail.component.html',
  styleUrls: ['./conference-detail.component.css']
})
export class ConferenceDetailComponent implements OnInit {

  conference: Conference;
  submissions: Array<BriefSubmission>;


  usersForm: FormGroup;
  reviewersForm: FormGroup;
  users: Array<User>;
  reviewers: Array<BriefUserRoles>;

  constructor(private fb: FormBuilder,
              private route: ActivatedRoute,
              private location: Location,
              private userService: UserService,
              private conferenceService: ConferenceService) {
    this.createForm();
  }

  ngOnInit() {
    this.route.data.subscribe(data => {
      this.conference = data.conference;
      this.submissions = this.conference.submissions;
    });
  }

  createForm() {
    this.usersForm = this.fb.group({
      search: [''],
    });
    this.reviewersForm = this.fb.group({
      search: [''],
    });
  }
  goBack(): void {
    this.location.back();
  }

}
