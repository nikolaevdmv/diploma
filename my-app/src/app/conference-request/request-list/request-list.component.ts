import {Component, OnInit, ViewChild} from '@angular/core';
import {ConferenceRequest} from '../../shared/model/conference-request/request.model';
import {ActivatedRoute} from '@angular/router';
import {FormBuilder, FormControl, FormGroup, Validators} from '@angular/forms';
import {TokenService} from '../../core/service/token.service';
import {BriefSubmission} from '../../shared/model/submission/brief-submission.model';
import {BriefConferenceRequest} from '../../shared/model/conference-request/brief-request.model';
import {Page} from '../../shared/model/paging/page.model';
import {RequestService} from '../../core/service/request.service';
import {UserService} from '../../core/service/user.service';
import {ConferenceRequestStatusService} from '../../core/service/utils/conference-request-status.service';

@Component({
  selector: 'app-request-list',
  templateUrl: './request-list.component.html',
  styleUrls: ['./request-list.component.css']
})
export class RequestListComponent implements OnInit {

  page: Page<BriefConferenceRequest>;

  form: FormGroup;
  requestType = ['Все', 'Принятые', 'Отклоненные', 'Ожидающие'];

  selectedType: number;

  constructor(private fb: FormBuilder,
              private route: ActivatedRoute,
              private requestSevice: RequestService,
              private tokenSevice: TokenService,
              private userSevice: UserService,
              private conferenceRequestStatusService: ConferenceRequestStatusService) {
    this.createForm();

  }

  //
  ngOnInit() {
    // this.route.data.subscribe(data => {
    //   this.page = data.conferenceRequestList;
    // });
    this.updateRequestsData();
  }

  pageChange(page: number) {
    this.page.number = page;
    this.updateRequestsData();
  }

  updateRequestsData() {
    if (this.tokenSevice.isAdmin()) {
      this.requestSevice.getAll(this.page ? this.page.number : 0, this.selectedType)
        .subscribe((data => this.page = data));
    } else {
      this.userSevice.getConferenceRequests(this.page ? this.page.number : 0, this.selectedType)
        .subscribe((data => this.page = data));
    }
  }

  submit(value: any) {
    switch (value.type) {
      case 'Все':
        this.selectedType = undefined;
        break;
      case 'Принятые':
        this.selectedType = this.conferenceRequestStatusService.getAcceptedNumber();
        break;
      case 'Отклоненные':
        this.selectedType = this.conferenceRequestStatusService.getDeclinedNumber();
        break;
      case 'Ожидающие':
        this.selectedType = this.conferenceRequestStatusService.getPendingNumber();
        break;
    }
    this.updateRequestsData();
  }

  createForm() {
    this.form = this.fb.group({
      type: ['', Validators.required],
    });
  }

}
