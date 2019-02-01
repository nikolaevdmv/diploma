import {Component, OnInit} from '@angular/core';
import {ActivatedRoute} from '@angular/router';
import {Submission} from '../../shared/model/submission/submission.model';
import {UserService} from '../../core/service/user.service';
import {BriefSubmission} from '../../shared/model/submission/brief-submission.model';

@Component({
  selector: 'app-user-submission-list',
  templateUrl: './user-submission-list.component.html',
  styleUrls: ['./user-submission-list.component.css']
})
// delete this
export class UserSubmissionListComponent implements OnInit {

  submissions: Array<BriefSubmission>;

  constructor(private route: ActivatedRoute,
              private userService: UserService) {
  }

  ngOnInit() {
    this.userService.getSubmissions().subscribe(data => this.submissions = data);
  }
}
