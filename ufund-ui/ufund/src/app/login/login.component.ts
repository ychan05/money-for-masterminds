import { HttpClient, HttpClientModule } from '@angular/common/http';
import { Component } from '@angular/core';
import { FormsModule } from '@angular/forms';
import { Router } from '@angular/router';

@Component({
  standalone: true,
  selector: 'app-login',
  templateUrl: './login.component.html',
  styleUrl: './login.component.css',
  imports: [FormsModule, HttpClientModule]
})
export class LoginComponent {
  loginObj: Login;
  constructor(private http: HttpClient, private router: Router) {
    this.loginObj = new Login();
  }

  onLogin(){
    this.http.get<Login>('http://localhost:8080/authenticator/' + this.loginObj.username).subscribe({
      next: data => {
        alert("Login success");
        console.log(data);
        if(data.username == "admin"){
          this.router.navigateByUrl('/manager')
        }
        else{
          this.router.navigateByUrl('/helper')
        }
    },
    error: error => {
      alert("Incorrect username");
    }
  })
  }

  onSignin(){
    console.log(this.loginObj);
    this.http.post('http://localhost:8080/authenticator/', this.loginObj).subscribe({
      next: data => {
        alert("Sign-in success");
        console.log(data);
      },
      error: error => {
        alert("User already exists");
      }
    })
  }
}

export class Login {
  username: string; 
  basket: Array<Object>;
  constructor() {
    this.username = '';
    this.basket = [];
  }
}