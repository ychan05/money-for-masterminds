import { Component } from '@angular/core';
import { Login, UserService } from '../user.service';

@Component({
  selector: 'app-navigation',
  templateUrl: './navigation.component.html',
  styleUrl: './navigation.component.css'
})
export class NavigationComponent {
  links: Array<string>;
  userService: UserService;

  constructor(userService: UserService){
    this.links = [];
    this.userService = userService;
    if(this.userService.loginObj.username == "admin"){
      this.links.push("/dashboard", "/cupboard");
      console.log(this.links);
    }
    else{
      this.links.push("/dashboard", "/funding-basket", "/cupboard")
      console.log(this.links);
    }
  }
}
