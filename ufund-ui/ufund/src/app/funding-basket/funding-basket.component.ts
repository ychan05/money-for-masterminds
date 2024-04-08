import { Component } from '@angular/core';
import { HelperService } from '../helper.service';
import { MessageService } from '../message.service';
import { Need } from '../need';
import { UserService } from '../user.service';
import { Router } from '@angular/router';

@Component({
  selector: 'app-funding-basket',
  templateUrl: './funding-basket.component.html',
  styleUrl: './funding-basket.component.css'
})
export class FundingBasketComponent {
  constructor(private helperService : HelperService, private messageService : MessageService, public userService: UserService, private router : Router) {}

  username : string = sessionStorage.getItem('user') || "";

  fundingBasket: Need[] = [];
  
  ngOnInit() {
    if (this.username === '') {
      this.router.navigate(['/login']);
    } else if (this.username === 'admin') {
      this.router.navigate(['/dashboard']);
    } else {
      this.getFundingBasket();
    }
  }

  getFundingBasket(): void {
    this.helperService.getFundingBasket(this.username)
        .subscribe(fundingBasket => this.fundingBasket = fundingBasket);
  }

  delete(username: string, needId: number) {
    this.fundingBasket = this.fundingBasket.filter(n => n.id !== needId);
    this.helperService.removeFromFundingBasket(username, needId).subscribe();
  }
}
