import { Component } from '@angular/core';

import { Need } from '../need';
import { NeedService } from '../need.service';
import { MessageService } from '../message.service';
import { UserService } from '../user.service';
import { HelperService } from '../helper.service';
import { Router } from '@angular/router';

@Component({
  selector: 'app-cupboard',
  templateUrl: './cupboard.component.html',
  styleUrl: './cupboard.component.css'
})
export class CupboardComponent {
  constructor(private needService: NeedService, private messageService: MessageService, public userService: UserService, private helperService: HelperService, private router : Router) {}
  needs: Need[] = [];
  username: string = sessionStorage.getItem('user') || "";

  getNeeds(): void {
    this.needService.getNeeds()
        .subscribe(needs => this.needs = needs);
  }

  ngOnInit() {
    if (this.username === '') {
      this.router.navigate(['/login']);
    } else {
      this.getNeeds();
    }
  }

  add (name: string, price: number, quantity: number): void {
    name = name.trim();
    if (!name) { return; }
    this.needService.addNeed({ name, price, quantity } as Need)
      .subscribe(need => {
        this.needs.push(need);
      });
  }

  addToFundingBasket(username: string, needId: number): void {
    this.helperService.addToFundingBasket(username, needId).subscribe();
  }

  delete(need: Need): void {
    this.needs = this.needs.filter(n => n !== need);
    this.needService.deleteNeed(need.id).subscribe();
  }

}
