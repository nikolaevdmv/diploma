import {Component, Input, OnInit} from '@angular/core';
import {Conference} from '../../shared/model/conference/conference.model';
import {ConferenceService} from '../../core/service/conference.service';
import {UserService} from '../../core/service/user.service';
import {TokenService} from '../../core/service/token.service';
import {ActivatedRoute} from '@angular/router';
import {BriefConference} from '../../shared/model/conference/brief-conference.model';
import {DomSanitizer} from '@angular/platform-browser';
import {fakeAsync} from '@angular/core/testing';


@Component({
  selector: 'app-conf-list',
  templateUrl: './user-conference-list.html',
  styleUrls: ['./user-conference-list.css']
})
export class UserConferenceListComponent implements OnInit {

  hideme: any = {};

  isReviewer: Map<number, boolean>;

  conferences: Array<BriefConference>;

  loading = false;

  constructor(private conferenceService: ConferenceService,
              private userService: UserService,
              private route: ActivatedRoute,
              private tokenService: TokenService, sanitizer: DomSanitizer) {
    this.isReviewer = new Map;
    // iconRegistry.addSvgIcon(
    //   'sync',
    //   sanitizer.bypassSecurityTrustResourceUrl('assets/img/sync.svg'));
  }

  ngOnInit() {
    this.userService.getCurrentUserConferences().subscribe(data => {
      this.conferences = data;
    });
  }

  reload() {
    this.loading = true;
    this.userService.getCurrentUserConferences().subscribe(
      data => {
        this.loading = false;
        this.conferences = data;
      }
    );
  }


}
