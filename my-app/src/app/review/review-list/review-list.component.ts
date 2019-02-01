import {Component, OnInit} from '@angular/core';
import {ActivatedRoute} from '@angular/router';
import {Review} from '../../shared/model/review/review.model';
import {SubmissionService} from '../../core/service/submission.service';
import {UserService} from '../../core/service/user.service';

@Component({
  selector: 'app-review-list',
  templateUrl: './review-list.component.html',
  styleUrls: ['./review-list.component.css']
})
export class ReviewListComponent implements OnInit {

  conferenceId: number;

  reviews: Array<Review>;

  constructor(private route: ActivatedRoute,
              private submissionService: SubmissionService,
              private userService: UserService) {
  }

  ngOnInit() {
    this.route.params.subscribe(params => {
      if (params['conferenceId']) {
        this.conferenceId = params['conferenceId'];
        this.userService.getReviewList(this.conferenceId)
          .subscribe(data => {
            this.reviews = data;
            console.log(data);
          });

      }
    });
  }


}
