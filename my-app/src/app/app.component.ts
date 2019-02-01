import {Component} from '@angular/core';
import {ModalService} from './core/service/modal.service';
import {AuthenticationService} from './authentication/authentication.service';

@Component({
  selector: 'app-root',
  templateUrl: './app.component.html',
  styleUrls: ['./app.component.css'],
})
export class AppComponent {
  title = 'app';
  state = 'default';

  constructor(private modalService: ModalService,
              public authService: AuthenticationService) {

  }

  removeModal() {
    this.modalService.destroy();
  }


}
