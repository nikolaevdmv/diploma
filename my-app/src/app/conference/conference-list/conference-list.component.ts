import {Component, OnInit, ViewChild, AfterViewInit} from '@angular/core';
import {ConferenceService} from '../../core/service/conference.service';
import {BriefConference} from '../../shared/model/conference/brief-conference.model';
import {Page} from '../../shared/model/paging/page.model';
import {FormBuilder, FormGroup, Validators} from '@angular/forms';
import {ConferenceStatusService} from '../../core/service/utils/conference-status.service';

@Component({
  selector: 'app-conference-list',
  templateUrl: './conference-list.component.html',
  styleUrls: ['./conference-list.component.css']
})
export class ConferenceListComponent implements OnInit {


  conferences: Array<BriefConference>;

  page: Page<BriefConference>;

  form: FormGroup;

  status: number;

  constructor(private fb: FormBuilder,
              private conferenceService: ConferenceService,
              public conferenceStatusService: ConferenceStatusService) {
    this.createForm();
  }

  ngOnInit() {
    // this.conferenceService.getConferences().subscribe(data => {
    //   this.conferences = data;
    // });
    this.updateConferenceData();
  }

  pageChange(page: number) {
    this.page.number = page;
    this.updateConferenceData();
  }

  updateConferenceData() {
    this.conferenceService.getConferences(this.page ? this.page.number : 0, this.status).subscribe((data => this.page = data));
  }

  createForm() {
    this.form = this.fb.group({
      status: ['Все', Validators.required],
    });
  }

  submit(value: any) {
    console.log(value);
    this.status = this.conferenceStatusService.getStatusNumber(value.status);
    this.updateConferenceData();
  }
}
