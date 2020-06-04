import { Component, OnInit } from '@angular/core';
import {FormBuilder, FormGroup, Validators} from "@angular/forms";
import {AuthService} from "../shared/services/auth.service";
import {Router} from "@angular/router";

@Component({
  selector: 'app-register-page',
  templateUrl: './register-page.component.html',
  styleUrls: ['./register-page.component.css']
})
export class RegisterPageComponent implements OnInit {

  showErrorMessage: boolean;
  registerForm: FormGroup;

  constructor(private formBuilder: FormBuilder, private auth: AuthService, private route: Router) {

  }

  ngOnInit(): void {
    this.registerForm = this.formBuilder.group({
      username: ['', Validators.compose([Validators.required, Validators.minLength(6)])],
      password: ['', Validators.compose([Validators.required, Validators.minLength(6)])],
      affiliation: ['', Validators.compose([])],
      phoneNumber: [null, Validators.compose([Validators.required])],
      email: ['', Validators.compose([Validators.required, Validators.email])],
      lastName: ['', Validators.compose([Validators.required])],
      firstName: ['', Validators.compose([Validators.required])],
      webPage : ['', Validators.compose([Validators.required])]
    });
    this.showErrorMessage = false;
  }

  signUP() {
    if (this.registerForm.invalid) {
      return;
    }
    this.auth.signup(this.username.value, this.password.value, this.firstName.value, this.lastName.value,
      this.email.value, this.phoneNumber.value, this.affiliation.value, this.webPage.value).subscribe(data =>{
        this.showErrorMessage = false;
        this.route.navigateByUrl('/main-page');
      },
      error => {
        this.showErrorMessage = true;
      });
  }

  get username() { return this.registerForm.get('username'); }

  get password() { return this.registerForm.get('password'); }

  get firstName() { return this.registerForm.get('firstName'); }

  get lastName() { return this.registerForm.get('lastName'); }

  get email() { return this.registerForm.get('email'); }

  get phoneNumber() { return this.registerForm.get('phoneNumber'); }

  get affiliation() { return this.registerForm.get('affiliation'); }

  get webPage() { return this.registerForm.get('webPage'); }

  backToLogin() {
    this.route.navigateByUrl('/login');
  }
}
