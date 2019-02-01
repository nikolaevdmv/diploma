import {Component, OnInit} from '@angular/core';
import {ActivatedRoute, Router} from '@angular/router';
import {Location} from '@angular/common';
import {ConferenceRequest} from '../../shared/model/conference-request/request.model';
import {RequestService} from '../../core/service/request.service';
import {ConferenceService} from '../../core/service/conference.service';
import {TokenService} from '../../core/service/token.service';
import {FormBuilder, FormGroup, Validators} from '@angular/forms';
import {BriefUser} from '../../shared/model/user/brief-user.model';
import {ConferenceRequestStatusService} from '../../core/service/utils/conference-request-status.service';

@Component({
  selector: 'app-conference-request-detail',
  templateUrl: './conference-request-detail.component.html',
  styleUrls: ['./conference-request-detail.component.css']
})
export class ConferenceRequestDetailComponent implements OnInit {
  form: FormGroup;

  conferenceRequest: ConferenceRequest;
  today;
  constructor(private fb: FormBuilder,
              private requestService: RequestService,
              private conferenceService: ConferenceService,
              private route: ActivatedRoute,
              private router: Router,
              private tokenService: TokenService,
              private location: Location,
              public conferenceRequestStatusService: ConferenceRequestStatusService) {
    this.today = new Date();
    this.today.setDate(this.today.getDate() + 1);
    this.today = this.today.toJSON().split('T')[0];
  }

  ngOnInit() {
    this.route.data.subscribe(data => {
      this.conferenceRequest = data.conferenceRequest;
      this.createForm();
      this.form.disable();
    });
  }


  onRequestSubmitted(value: any) {
    // this.conferenceRequest.comment = value.comment;
    this.conferenceRequest.status = value.status;
    this.requestService.createComment(this.conferenceRequest.id, value)
      .subscribe(res => this.conferenceRequest = res);
    // this.requestService.update(this.conferenceRequest)
    //   .subscribe(() => this.router.navigate(['/conference-requests'])
    //   );
  }

  isAdmin(): boolean {
    return this.tokenService.isAdmin();
  }

  goBack(): void {
    this.location.back();
  }

  createForm() {
    this.form = this.fb.group({
      title: [this.conferenceRequest.title, Validators.required],
      acronym: [this.conferenceRequest.acronym, Validators.required],
      webPage: [this.conferenceRequest.webPage, Validators.required],
      country: [this.conferenceRequest.country, Validators.required],
      city: [this.conferenceRequest.city, Validators.required],
      expirationDate: [new Date(this.conferenceRequest.expirationDate).toJSON().split('T')[0], Validators.required],
    });
  }

  resendRequest(request: ConferenceRequest) {
    request.id = this.conferenceRequest.id;
    request.organizer = new BriefUser(this.tokenService.getUsername());
    request.status = this.conferenceRequestStatusService.getPendingNumber();
    console.log(request);
    this.requestService.update(request)
      .subscribe(() => this.router.navigate(['/conference-requests']));
  }

}
