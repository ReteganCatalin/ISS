import { Component, OnInit } from '@angular/core';
import {FormBuilder, FormGroup, Validators} from "@angular/forms";
import {AuthService} from "../services/auth.service";

@Component({
  selector: 'app-login-page',
  templateUrl: './login-page.component.html',
  styleUrls: ['./login-page.component.css']
})

export class LoginPageComponent implements OnInit {

  loginForm: FormGroup;
  showErrorMessage: boolean;

  constructor(private formBuilder: FormBuilder, private auth: AuthService) {
  }

  ngOnInit(): void {
    this.loginForm = this.formBuilder.group({
      username: ['', Validators.compose([Validators.required, Validators.minLength(6)])],
      password: ['', Validators.compose([Validators.required, Validators.minLength(6)])]
    });
    this.showErrorMessage = true;
  }

  login() {
    if (this.loginForm.invalid) {
      return;
    }
    this.auth.login(this.username.value, this.password.value).subscribe(data =>{
      console.log('merge');
    },
    error => {
      this.showErrorMessage = true;
    });
  }

  get username() { return this.loginForm.get('username'); }

  get password() { return this.loginForm.get('password'); }
}
