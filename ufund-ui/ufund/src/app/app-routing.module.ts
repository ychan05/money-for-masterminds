import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';

import { CupboardComponent } from './cupboard/cupboard.component';
import { DashboardComponent } from './dashboard/dashboard.component';
import { NeedDetailComponent } from './need-detail/need-detail.component';
import { LoginComponent } from './login/login.component';
import { FundingBasketComponent } from './funding-basket/funding-basket.component';
import { RiddleDetailComponent } from './riddle-detail/riddle-detail.component';
import { RiddlesComponent } from './riddles/riddles.component';

const routes: Routes = [
  { path: '', redirectTo: '/login', pathMatch: 'full'},
  { path: 'login', component: LoginComponent},
  { path: 'detail/:id', component: NeedDetailComponent},
  { path: 'cupboard', component: CupboardComponent},
  { path: 'funding-basket', component: FundingBasketComponent},
  { path: 'dashboard', component: DashboardComponent}, 
  { path: 'riddle/:id', component: RiddleDetailComponent},
  { path: 'riddles', component: RiddlesComponent}
];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule { }
