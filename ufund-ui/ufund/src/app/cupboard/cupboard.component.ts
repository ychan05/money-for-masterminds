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

  getNeeds(): void {
    this.needService.getNeeds()
        .subscribe(needs => this.needs = needs);
  }

  ngOnInit() {
    this.getNeeds();
  }

  needs: Need[] = [];
  selectedNeed?: Need;

  onSelect(need: Need): void {
    this.selectedNeed = need;
    this.messageService.add(`CupboardComponent: Selected need id=${need.id}`);
  }
}
