import {Component, OnInit} from '@angular/core';
import {FormBuilder, FormControl, FormGroup, Validators} from '@angular/forms';
import {RequestService} from '../../core/service/request.service';
import {Router} from '@angular/router';
import {ConferenceRequest} from '../../shared/model/conference-request/request.model';
import {TokenService} from '../../core/service/token.service';
import {BriefUser} from '../../shared/model/user/brief-user.model';
import {ConferenceRequestStatusService} from '../../core/service/utils/conference-request-status.service';

@Component({
  selector: 'app-new-conference-request',
  templateUrl: './new-conference-request.component.html',
  styleUrls: ['./new-conference-request.component.css']
})
export class NewConferenceRequestComponent implements OnInit {

  form: FormGroup;

  // today = new Date().toJSON().split('T')[0];
  today;

  constructor(private fb: FormBuilder,
              private requestService: RequestService,
              private router: Router,
              private tokenService: TokenService,
              public conferenceRequestStatusService: ConferenceRequestStatusService) {
    this.createForm();
    this.today = new Date();
    this.today.setDate(this.today.getDate() + 1);
    this.today = this.today.toJSON().split('T')[0];
  }

  ngOnInit() {
  }

  createForm() {
    this.form = this.fb.group({
      title: ['', Validators.required],
      acronym: ['', Validators.required],
      webPage: ['', [Validators.required]],
      country: ['', Validators.required],
      city: ['', Validators.required],
      expirationDate: [Date, Validators.required],
    });
  }

  submit(request: ConferenceRequest) {
    request.status = this.conferenceRequestStatusService.getPendingNumber();
    request.organizer = new BriefUser(this.tokenService.getUsername());
    console.log(request);
    this.requestService.createRequest(request)
      .subscribe(() => this.router.navigate(['/conference-requests']));
  }
}


