import { NgModule } from '@angular/core';
import { BrowserModule } from '@angular/platform-browser';
import { HttpClientModule } from '@angular/common/http';
import { AppRoutingModule } from './app-routing.module';
import { AppComponent } from './app.component';
import { CupboardComponent } from './cupboard/cupboard.component';
import { FormsModule, NgModel } from '@angular/forms';
import { NeedDetailComponent } from './need-detail/need-detail.component';
import { MessagesComponent } from './messages/messages.component';
import { DashboardComponent } from './dashboard/dashboard.component';
import { NeedSearchComponent } from './need-search/need-search.component';

@NgModule({
  declarations: [
    AppComponent,
    CupboardComponent,
    NeedDetailComponent,
    MessagesComponent,
    DashboardComponent,
    NeedSearchComponent,
  ],
  imports: [
    BrowserModule,
    AppRoutingModule,
    FormsModule,
    HttpClientModule
  ],
  providers: [],
  bootstrap: [AppComponent]
})
export class AppModule { }
