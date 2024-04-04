import { HttpClient, HttpHeaders } from '@angular/common/http';
import { ChangeDetectorRef, Injectable } from '@angular/core';
import { MessageService } from './message.service';
import { Router } from '@angular/router';
import { Observable, catchError, of, tap } from 'rxjs';
import { Need } from './need';
import { AppComponent } from './app.component';
import { NavigationComponent } from './navigation/navigation.component';
import { HelperService } from './helper.service';

@Injectable({
  providedIn: 'root'
})
export class UserService {
  private nav : NavigationComponent;
  public loginObj: Login;
  appComponent: AppComponent;
  constructor(private http: HttpClient, private messageService: MessageService, private router: Router) { 
      this.loginObj = new Login();
      this.appComponent = new AppComponent();
      this.nav = new NavigationComponent(this);
    }
    

  httpOptions = {
    headers: new HttpHeaders({ 'Content-Type': 'application/json' })

  };

  private authenticatorUrl = 'http://localhost:8080/authenticator'

  private log(message: string) {
    this.messageService.add(`UserService: ${message}`);
  }

  // WILL PROBABLY NEED TO BE REWORKED A LITTLE BUT THIS WILL WORK FOR NOW
  // TO-DO: ADD MANAGER AND HELPER URLS
  login(username: string, password: string){
    const url = `${this.authenticatorUrl}/${username}/${password}`;
    this.http.get<Login>(url).subscribe({
      next: data => {
        alert("Login success");
        console.log(data);
        this.loginObj.username = username;
        this.loginObj.password = password;
        this.loginObj.basket = data.basket;
        this.nav = new NavigationComponent(this);
        this.router.navigateByUrl("/dashboard");
      },
      error: error => {
        alert("Incorrect username");
      }
    })
  }

  signin(user: Login){
    return this.http.post<Login>(this.authenticatorUrl, user, this.httpOptions).subscribe({
      next: data => {
        alert("Account successfully created");
      },
      error: error => {
        alert("Cannot create an account with this username")
      }
      
    });
  }

}
export class Login {
  username: string; 
  password: string;
  basket: Array<Need>;
  constructor() {
    this.username = '';
    this.basket = [];
    this.password = '';
  }
}
