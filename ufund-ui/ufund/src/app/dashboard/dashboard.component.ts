import { Component } from '@angular/core';

import { Need } from '../need';
import { NeedService } from '../need.service';
import { UserService } from '../user.service';

@Component({
  selector: 'app-dashboard',
  templateUrl: './dashboard.component.html',
  styleUrl: './dashboard.component.css'
})
export class DashboardComponent {
  needs: Need[] = [];

  constructor(private needService: NeedService) { }

  ngOnInit() {
    this.getNeeds();
  }

  getNeeds(): void {
    this.needService.getNeeds()
      .subscribe(needs => this.needs = needs.slice(1, 5));
  }
}
