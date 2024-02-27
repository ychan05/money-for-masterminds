import { Component } from '@angular/core';
import { 
  NgFor,
  NgIf,
  UpperCasePipe
 } from '@angular/common';

import { Need } from '../need';
import { NEEDS } from '../mock-needs';

@Component({
  selector: 'app-cupboard',
  templateUrl: './cupboard.component.html',
  styleUrl: './cupboard.component.css'
})
export class CupboardComponent {
  needs = NEEDS;
  selectedNeed?: Need;

  onSelect(need: Need): void {
    this.selectedNeed = need;
  }
}
