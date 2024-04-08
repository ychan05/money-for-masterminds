import { Component } from '@angular/core';

import { Riddle } from '../riddle';
import { RiddlesComponent
 } from '../riddles/riddles.component';
@Component({
  selector: 'app-riddle-form',
  templateUrl: './riddle-form.component.html',
  styleUrls: ['./riddle-form.component.css']
})
export class RiddleFormComponent {
  constructor(private riddlesComponent: RiddlesComponent) { }

  model = new Riddle(1, 'What has keys but can’t open locks?', 'A piano');
  submitted = false;

  onSubmit() {
    this.riddlesComponent.add(this.model.question, this.model.answer);
    this.submitted = true;
  }

  newRiddle() {
    this.model = new Riddle(2, '', '');
  }
}