import {Component, OnInit} from '@angular/core';
import {FormBuilder, FormGroup, Validators} from '@angular/forms';
import {AuthenticationService} from '../../authentication/authentication.service';
import {Router} from '@angular/router';
import {ModalService} from '../../core/service/modal.service';

@Component({
  selector: 'app-signup-modal',
  templateUrl: './signup-modal.component.html',
  styleUrls: ['./signup-modal.component.css']
})
export class SignupModalComponent implements OnInit {

  form: FormGroup;

  error: {
    password: string[],
    email: string
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
      firstname: ['', Validators.required],
      lastname: ['', Validators.required],
      email: ['', [Validators.required, Validators.pattern('^[_A-Za-z0-9-\\+]+(\\.[_A-Za-z0-9-]+)*@'
        + '[A-Za-z0-9-]+(\\.[A-Za-z0-9]+)*(\\.[A-Za-z]{2,})$')]],
      password: ['', Validators.required],
      passwordRetype: ['', Validators.required],
    });
  }

  submit(value: any) {
    this.error = {password: [''], email: ''};
    if (this.form.value.password !== this.form.value.passwordRetype) {
      this.error.password = ['Пароли не совпадают'];
    } else {
      delete value.passwordRetype;
      this.auth.doSignup(value)
        .subscribe(
          data => {
            this.router.navigate(['/']);
            this.modalService.destroy();
          },
          error => {
            this.error = error.error.errors;
          }
        );
    }
  }

  close() {
    this.modalService.destroy();
  }

}
