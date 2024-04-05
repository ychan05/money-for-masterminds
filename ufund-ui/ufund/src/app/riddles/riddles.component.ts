import { Component } from '@angular/core';

import { Riddle } from '../riddle';
import { RiddleService } from '../riddle.service';
import { MessageService } from '../message.service';
import { UserService } from '../user.service';
import { HelperService } from '../helper.service';
import { Router } from '@angular/router';

@Component({
  selector: 'app-cupboard',
  templateUrl: './riddles.component.html',
  styleUrls: ['./riddles.component.css']
})
export class RiddlesComponent {
  constructor(private riddleService: RiddleService, private messageService: MessageService, public userService: UserService, private helperService: HelperService, private router : Router) { }
  riddles: Riddle[] = [];
  username: string = sessionStorage.getItem('user') || "";

  getRiddles(): void {
    this.riddleService.getRiddles()
      .subscribe(riddles => this.riddles = riddles);
  }

  ngOnInit() {
    if (this.username !== 'admin') {
      if (this.username === '') {
        this.router.navigate(['/login']);
      } else {
        this.router.navigate(['/dashboard']);
      }
    } else {
      this.getRiddles();
    }
  }

  add(question: string, answer: string): void {
    question = question.trim();
    if (!question) { return; }
    this.riddleService.addRiddle({ question, answer } as Riddle)
      .subscribe(riddle => {
        this.riddles.push(riddle);
      });
  }

  delete(riddle: Riddle): void {
    this.riddles = this.riddles.filter(r => r !== riddle);
    this.riddleService.deleteRiddle(riddle.id).subscribe();
  }

}