import { Component } from '@angular/core';

import { Need } from '../need';
import { NEEDS } from '../mock-needs';
import { NeedService } from '../need.service';
import { MessageService } from '../message.service';

@Component({
  selector: 'app-cupboard',
  templateUrl: './cupboard.component.html',
  styleUrl: './cupboard.component.css'
})
export class CupboardComponent {
  constructor(private needService: NeedService, private messageService: MessageService) {}
  needs: Need[] = [];

  getNeeds(): void {
    this.needService.getNeeds()
        .subscribe(needs => this.needs = needs);
  }

  ngOnInit() {
    this.getNeeds();
  }

  add (name: string, price: number, quantity: number): void {
    name = name.trim();
    if (!name) { return; }
    this.needService.addNeed({ name, price, quantity } as Need)
      .subscribe(need => {
        this.needs.push(need);
      });
  }

  delete(need: Need): void {
    this.needs = this.needs.filter(n => n !== need);
    this.needService.deleteNeed(need.id).subscribe();
  }

}
