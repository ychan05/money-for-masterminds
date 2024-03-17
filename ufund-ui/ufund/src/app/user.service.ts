import { HttpClient, HttpHeaders } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { MessageService } from './message.service';
import { Router } from '@angular/router';
import { Observable, catchError, of, tap } from 'rxjs';
import { Need } from './need';

@Injectable({
  providedIn: 'root'
})
export class UserService {
  loginObj: Login;
  constructor(private http: HttpClient, private messageService: MessageService, private router: Router) { 
      this.loginObj = new Login();
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
  login(username: string){
    const url = `${this.authenticatorUrl}/${username}`;
    this.http.get<Login>(url).subscribe({
      next: data => {
        alert("Login success");
        console.log(data);
        this.loginObj.username = username;
        this.loginObj.basket = data.basket;
        if(this.loginObj.username == "admin"){
          // this.router.navigateByUrl(MANAGER-PATH)
        }
        else{
          // this.router.navigateByUrl(HELPER-PATH)
        }
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
        alert("Cannot create an account with this username");
      }
      
    });
  }

  private handleError<T>(operation = 'operation', result?: T) {
    return (error: any): Observable<T> => {

      console.error(error); // log to console instead

      this.log(`${operation} failed: ${error.message}`);

      alert(error.message);

      return of(result as T);
    };
  }
}
export class Login {
  username: string; 
  basket: Array<Need>;
  constructor() {
    this.username = '';
    this.basket = [];
  }
}
