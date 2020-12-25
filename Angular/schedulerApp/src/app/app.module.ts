import { BrowserModule } from '@angular/platform-browser';
import { NgModule } from '@angular/core';

import { SchedulerModule } from './scheduler/scheduler.module';

import { AppRoutingModule } from './app-routing.module';
import { AppComponent } from './app.component';

@NgModule({
  declarations: [
    AppComponent
  ],
  imports: [
    BrowserModule,
    AppRoutingModule,
    SchedulerModule
  ],
  providers: [],
  bootstrap: [AppComponent]
})
export class AppModule { }
