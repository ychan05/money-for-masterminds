import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';

import { CupboardComponent } from './cupboard/cupboard.component';
import { DashboardComponent } from './dashboard/dashboard.component';
import { NeedDetailComponent } from './need-detail/need-detail.component';

const routes: Routes = [
  { path: '', redirectTo: '/dashboard', pathMatch: 'full'},
  { path: 'dashboard', component: DashboardComponent},
  { path: 'detail/:id', component: NeedDetailComponent},
  { path: 'cupboard', component: CupboardComponent}
];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule { }
