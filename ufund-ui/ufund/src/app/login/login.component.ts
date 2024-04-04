import { HttpClient, HttpClientModule } from '@angular/common/http';
import { Component } from '@angular/core';
import { FormsModule } from '@angular/forms';
import { Router } from '@angular/router';
import { Login, UserService } from '../user.service';

@Component({
  standalone: true,
  selector: 'app-login',
  templateUrl: './login.component.html',
  styleUrl: './login.component.css',
  imports: [FormsModule, HttpClientModule]
})
export class LoginComponent {
  username: string;
  password: string;
  newUser: Login;
  constructor(public userService: UserService){
    this.newUser = new Login();
    this.username = "";
    this.password = "";
  }

  onLogIn(username: string, password: string){
    this.userService.login(username, password);
  }

  onSignIn(username: string, password: string){
    this.newUser.username = username;
    this.newUser.password = password;
    this.userService.signin(this.newUser);
  }
}