import { Component } from '@angular/core';
import { HelperService } from '../helper.service';
import { MessageService } from '../message.service';
import { Need } from '../need';

@Component({
  selector: 'app-funding-basket',
  templateUrl: './funding-basket.component.html',
  styleUrl: './funding-basket.component.css'
})
export class FundingBasketComponent {
  constructor(private helperService : HelperService, private messageService : MessageService) {}

  username : string = 'Gradono'; // TODO: get username from session

  fundingBasket: Need[] = [];

  getFundingBasket(): void {
    this.helperService.getFundingBasket(this.username)
        .subscribe(fundingBasket => this.fundingBasket = fundingBasket);
  }

  ngOnInit() {
    this.getFundingBasket();
  }
}
