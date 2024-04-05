import { Component } from '@angular/core';

import { Need } from '../need';
import { NeedService } from '../need.service';
import { UserService } from '../user.service';
import { Router } from '@angular/router';

@Component({
  selector: 'app-dashboard',
  templateUrl: './dashboard.component.html',
  styleUrl: './dashboard.component.css'
})
export class DashboardComponent {
  username: string = sessionStorage.getItem('user') || "";
  needs: Need[] = [];

  constructor(private needService: NeedService, private router : Router) { }

  ngOnInit() {
    if (this.username === '') {
      this.router.navigate(['/login']);
    } else {
      this.getNeeds();
    }
  }

  getNeeds(): void {
    this.needService.getNeeds()
      .subscribe(needs => this.needs = needs.slice(1, 5));
  }
}
