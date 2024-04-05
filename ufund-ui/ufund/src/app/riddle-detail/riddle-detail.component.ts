import { Component, Input } from '@angular/core';
import { ActivatedRoute, Router } from '@angular/router';
import { UserService } from '../user.service';
import { Riddle } from '../riddle';
import { RiddleService } from '../riddle.service';
import { Location } from '@angular/common';

@Component({
  selector: 'app-riddle-detail',
  templateUrl: './riddle-detail.component.html',
  styleUrl: './riddle-detail.component.css'
})
export class RiddleDetailComponent {
  username: string = sessionStorage.getItem('user') || "";

  constructor(
    private route: ActivatedRoute,
    private riddleService: RiddleService,
    private location: Location,
    public userService: UserService,
    public router: Router
  ) {}

  ngOnInit(): void {
    if (this.username !== 'admin') {
      if (this.username === '') {
        this.router.navigate(['/login']);
      } else {
        this.router.navigate(['/dashboard']);
      }
    }
    this.getRiddle();
  }

  getRiddle(): void {
    const id = Number(this.route.snapshot.paramMap.get('id'));
    this.riddleService.getRiddle(id)
      .subscribe(riddle => this.riddle = riddle);
  }

  goBack(): void {
    this.location.back();
  }

  save(): void {
    if (this.riddle) {
      this.riddleService.updateRiddle(this.riddle)
        .subscribe(() => this.goBack());
    }
  }

  @Input() riddle?: Riddle;
}
