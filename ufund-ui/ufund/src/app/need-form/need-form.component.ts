import { Component } from '@angular/core';

import { Need } from '../need';
import { CupboardComponent } from '../cupboard/cupboard.component';

@Component({
  selector: 'app-need-form',
  templateUrl: './need-form.component.html',
  styleUrl: './need-form.component.css'
})
export class NeedFormComponent {
  constructor(private cupboardComponent: CupboardComponent) { }

  model = new Need(1, 'Ray gun', 100, 1);
  submitted = false;
  onSubmit() {
    this.cupboardComponent.add(this.model.name, this.model.price, this.model.quantity);
    this.submitted = true;
  }

  newNeed() {
    this.model = new Need(2, '', 0, 0);
  }
}
