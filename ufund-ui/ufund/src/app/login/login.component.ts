import { HttpClient, HttpClientModule } from '@angular/common/http';
import { Component, Input } from '@angular/core';
import { FormsModule } from '@angular/forms';
import { Router } from '@angular/router';
import { Login, UserService } from '../user.service';
import { RiddleService } from '../riddle.service';
import { Riddle } from '../riddle';
import { Observable, map } from 'rxjs';

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
  userAnswer: string;
  riddle: Riddle;
  constructor(public userService: UserService, public riddleService: RiddleService){
    this.newUser = new Login();
    this.username = "";
    this.password = "";
    this.userAnswer = "";
    this.riddle = new Riddle(0, "", "");
  }

  ngOnInit(): void {
    this.getRandomRiddle();
  }


  getRandomRiddle(): void {
    this.riddleService.getRandomRiddle().subscribe(riddle => this.riddle = riddle);
  }
  
  onLogIn(username: string, password: string, userAnswer: string){
    userAnswer = userAnswer.toLowerCase().replace(/\s/g, '').replace(/[^a-zA-Z0-9]/g, '');
    const actualAnswer = this.riddle.answer.toLowerCase().replace(/\s/g, '').replace(/[^a-zA-Z0-9]/g, '');
    if (userAnswer != actualAnswer){
      alert("Incorrect answer to riddle");
      return;
    }
    this.userService.login(username, password);
  }

  onSignIn(username: string, password: string){
    this.newUser.username = username;
    this.newUser.password = password;
    this.userService.signin(this.newUser);
  }
}