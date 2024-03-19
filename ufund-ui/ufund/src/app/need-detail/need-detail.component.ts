import { Component, Input} from '@angular/core';
import { Location } from '@angular/common';
import { ActivatedRoute } from '@angular/router';

import { Need } from '../need';
import { NeedService } from '../need.service';
import { UserService } from '../user.service';
import { HelperService } from '../helper.service';

@Component({
  selector: 'app-need-detail',
  templateUrl: './need-detail.component.html',
  styleUrl: './need-detail.component.css'
})
export class NeedDetailComponent {
  username: string = '';

  constructor(
    private route: ActivatedRoute,
    private needService: NeedService,
    private location: Location,
    public userService: UserService,
    public helperService: HelperService
  ) {}

  ngOnInit(): void {
    this.username = this.userService.loginObj.username;
    this.getNeed();
  }

  getNeed(): void {
    const id = Number(this.route.snapshot.paramMap.get('id'));
    this.needService.getNeed(id)
      .subscribe(need => this.need = need);
  }

  goBack(): void {
    this.location.back();
  }

  save(): void {
    if (this.need) {
      this.needService.updateNeed(this.need)
        .subscribe(() => this.goBack());
    }
  }

  addToFundingBasket(username: string, needId: number): void {
    this.helperService.addToFundingBasket(username, needId).subscribe();
  }

  @Input() need?: Need;
}
