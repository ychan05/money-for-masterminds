import { Component } from '@angular/core';
import { Login, UserService } from '../user.service';

@Component({
  selector: 'app-navigation',
  templateUrl: './navigation.component.html',
  styleUrl: './navigation.component.css'
})
export class NavigationComponent {
  links: Array<string>;
  username: string = sessionStorage.getItem('user') || "";

    constructor(public userService: UserService){
    this.links = [];
    if(this.username == "admin"){
      this.links.push("/dashboard", "/cupboard", "/riddles");
      console.log(this.links);
    }
    else{
      this.links.push("/dashboard", "/funding-basket", "/cupboard")
      console.log(this.links);
    }
  }

  logout(): void {
    this.userService.logout();
  }
}
