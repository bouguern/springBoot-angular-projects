import {DataServiceService} from "./data-service.service";
import {BrowserModule} from "@angular/platform-browser";
import {NgModule} from "@angular/core";
import {SchedulerComponent} from "./scheduler/scheduler.component";
import {DayPilotModule} from "daypilot-pro-angular";
import {HttpClientModule} from "@angular/common/http";

@NgModule({
  imports: [
    BrowserModule,
    HttpClientModule,
    DayPilotModule
  ],
  declarations: [
    SchedulerComponent
  ],
  exports: [SchedulerComponent],
  providers: [DataServiceService]
})
export class SchedulerModule {
}