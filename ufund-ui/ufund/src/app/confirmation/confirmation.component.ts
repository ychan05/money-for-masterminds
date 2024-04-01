import { Component, OnInit } from '@angular/core';
import { Router } from '@angular/router';
import { HelperService } from '../helper.service';

@Component({
  selector: 'app-confirmation',
  templateUrl: './confirmation.component.html',
  styleUrls: ['./confirmation.component.css']
})
export class ConfirmationComponent implements OnInit {
  checkedoutNeeds: any[] = [];

  constructor(private router: Router, private helperService: HelperService) { }

  ngOnInit(): void {
    this.getCheckedoutNeeds();
  }

  getCheckedoutNeeds(): void {
  }

  checkout(): void {
  }
}