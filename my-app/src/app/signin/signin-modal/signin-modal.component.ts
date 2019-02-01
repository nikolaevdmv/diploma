import {Component, OnInit} from '@angular/core';
import {FormBuilder, FormGroup, Validators} from '@angular/forms';
import {AuthenticationService} from '../../authentication/authentication.service';
import {Router} from '@angular/router';
import {ModalService} from '../../core/service/modal.service';
import {animate, state, style, transition, trigger} from '@angular/animations';

@Component({
  selector: 'app-signin-modal',
  templateUrl: './signin-modal.component.html',
  styleUrls: ['./signin-modal.component.css'],
  animations: [
    trigger('flyInOut', [
      state('in', style({transform: 'translateY(0)', opacity: 1})),
      transition('void => *', [
        style({transform: 'translateY(-50%)', opacity: 0}),
        animate(400)
      ]),
      transition('* => void', [
        animate(400, style({transform: 'translateY(100%)', opacity: 0}))
      ])
    ])
  ],
})
export class SigninModalComponent implements OnInit {

  form: FormGroup;
  error: {
    error: string
  };

  constructor(private fb: FormBuilder,
              private auth: AuthenticationService,
              private router: Router,
              private modalService: ModalService) {
    this.createForm();
  }

  ngOnInit() {
  }

  createForm() {
    this.form = this.fb.group({
      username: ['', Validators.required],
      password: ['', Validators.required]
    });
  }

  submit(value: any) {
    this.error = null;
    this.auth.doSignin(value)
      .subscribe(
        data => {
          this.router.navigate(['/']);
          this.modalService.destroy();
        },
        error => this.error = error.error.errors
      );
  }

  close() {
    this.modalService.destroy();
  }

}
