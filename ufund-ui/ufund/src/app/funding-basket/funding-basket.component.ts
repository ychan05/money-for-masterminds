import { Component } from '@angular/core';
import { Router } from '@angular/router';
import { HelperService } from '../helper.service';
import { MessageService } from '../message.service';
import { Need } from '../need';
import { UserService } from '../user.service';

@Component({
  selector: 'app-funding-basket',
  templateUrl: './funding-basket.component.html',
  styleUrl: './funding-basket.component.css'
})
export class FundingBasketComponent {
  constructor(private helperService : HelperService, private messageService : MessageService, public userService: UserService, private router: Router) {}

  username : string = '';

  fundingBasket: Need[] = [];
  
  ngOnInit() {
    this.username = this.userService.loginObj.username;
    this.getFundingBasket();
  }

  getFundingBasket(): void {
    this.helperService.getFundingBasket(this.username)
        .subscribe(fundingBasket => this.fundingBasket = fundingBasket);
  }

  delete(username: string, needId: number) {
    this.fundingBasket = this.fundingBasket.filter(n => n.id !== needId);
    this.helperService.removeFromFundingBasket(username, needId).subscribe();
  }

  checkout(): void {
    if (this.fundingBasket.length === 0) {
      alert('Your funding basket is empty. There is nothing to checkout.');
      return;
    }

    const checkoutConfirmed = window.confirm('Are you sure you want to checkout the needs in your funding basket?');

    if (checkoutConfirmed) {
      this.helperService.checkoutNeeds(this.username).subscribe(
        () => {
          alert('Checkout successful!');
          this.fundingBasket = [];
        },
        (error) => {
          console.error('Error during checkout:', error);
          alert('Error during checkout. Please try again later.');
        }
      );
    }
  }
}