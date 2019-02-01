import {Component, OnInit} from '@angular/core';
import {Location} from '@angular/common';
import {ConferenceService} from '../../core/service/conference.service';
import {ConferenceStatistic} from '../../shared/model/conference-statistic/conference-statistic.model';
import {ActivatedRoute} from '@angular/router';

@Component({
  selector: 'app-conference-statistic',
  templateUrl: './conference-statistic.component.html',
  styleUrls: ['./conference-statistic.component.css']
})
export class ConferenceStatisticComponent implements OnInit {

  conferenceId: number;
  statistic: ConferenceStatistic;

  constructor(private route: ActivatedRoute,
              private location: Location,
              private conferenceService: ConferenceService) {
  }

  ngOnInit() {
    this.route.params.subscribe(params => {
      if (params['conferenceId']) {
        this.conferenceId = params['conferenceId'];
        this.conferenceService.getStatistic(this.conferenceId).subscribe(value => this.statistic = value);
      }
    });
  }


  goBack(): void {
    this.location.back();
  }
}
