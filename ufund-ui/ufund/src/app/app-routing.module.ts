import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';

import { CupboardComponent } from './cupboard/cupboard.component';
import { DashboardComponent } from './helper/dashboard/dashboard.component';
import { NeedDetailComponent } from './need-detail/need-detail.component';
import { LoginComponent } from './login/login.component';
import { FundingBasketComponent } from './funding-basket/funding-basket.component';

const routes: Routes = [
  { path: '', redirectTo: '/login', pathMatch: 'full'},
  { path: 'login', component: LoginComponent},
  { path: 'detail/:id', component: NeedDetailComponent},
  { path: 'cupboard', component: CupboardComponent},
  { path: 'funding-basket', component: FundingBasketComponent}
];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule { }
