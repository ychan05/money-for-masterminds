import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';

import { CupboardComponent } from './cupboard/cupboard.component';
import { DashboardComponent } from './helper/dashboard/dashboard.component';
import { NeedDetailComponent } from './need-detail/need-detail.component';
import { LoginComponent } from './login/login.component';
import { HelperComponent } from './helper/helper.component';
import { ManagerComponent } from './manager/manager.component';

const routes: Routes = [
  { path: '', redirectTo: '/login', pathMatch: 'full'},
  { path: 'login', component: LoginComponent},
  { path: 'detail/:id', component: NeedDetailComponent},
  { path: 'cupboard', component: CupboardComponent},
  { path: 'helper', component: HelperComponent},
  { path: 'manager', component: ManagerComponent}
];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule { }
