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
  newUser: Login;
  constructor(public userService: UserService){
    this.newUser = new Login();
    this.username = "";
  }

  onLogIn(username: string){
    this.userService.login(username);
  }

  onSignIn(username: string){
    this.newUser.username = username;
    this.userService.signin(this.newUser);
  }
}