import { Component, OnInit } from '@angular/core';
import {FormBuilder, FormGroup, Validators} from "@angular/forms";
import {AuthService} from "../shared/services/auth.service";
import {ActivatedRoute, Router} from "@angular/router";

@Component({
  selector: 'app-login-page',
  templateUrl: './login-page.component.html',
  styleUrls: ['./login-page.component.css']
})

export class LoginPageComponent implements OnInit {

  loginForm: FormGroup;
  showErrorMessage: boolean;

  constructor(private formBuilder: FormBuilder, private auth: AuthService, private route: Router) {
  }

  ngOnInit(): void {
    this.loginForm = this.formBuilder.group({
      username: ['', Validators.compose([Validators.required, Validators.minLength(4)])],
      password: ['', Validators.compose([Validators.required, Validators.minLength(4)])]
    });
    this.showErrorMessage = false;
  }

  login() {
    if (this.loginForm.invalid) {
      return;
    }
    this.auth.login(this.username.value, this.password.value).subscribe(data =>{
      this.showErrorMessage = false;
      this.route.navigateByUrl('/main-page');
    },
    error => {
      this.showErrorMessage = true;
    });
  }

  get username() { return this.loginForm.get('username'); }

  get password() { return this.loginForm.get('password'); }
}
