import { Component, OnInit } from '@angular/core';
import { Router } from '@angular/router';
import { HelperService } from '../helper.service';
import { UserService } from '../user.service';

@Component({
  selector: 'app-confirmation',
  templateUrl: './confirmation.component.html',
  styleUrls: ['./confirmation.component.css']
})
export class ConfirmationComponent implements OnInit {
  checkedoutNeeds: any[] = [];

  username : string = '';

  constructor(private router: Router, private helperService: HelperService, public userService: UserService) { }

  ngOnInit(): void {
    this.username = this.userService.loginObj.username;
    this.getCheckedoutNeeds();
  }

  getCheckedoutNeeds(): void {
  }

  checkout(): void {

  }
}