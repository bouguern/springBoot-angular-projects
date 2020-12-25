import { AfterViewInit, Component, ViewChild } from '@angular/core';
import { DayPilot, DayPilotSchedulerComponent } from "daypilot-pro-angular";
import { DataServiceService } from '../data-service.service';

@Component({
  selector: 'app-scheduler',
  template: `
      <div class="body">
        <h1>Scheduler</h1>
        <daypilot-scheduler [config]="config" [events]="events" #scheduler></daypilot-scheduler>
      </div>
  `,
  styles: [`
    .body {
      padding: 10px;
    }
  `]
})
export class SchedulerComponent implements AfterViewInit{

  @ViewChild("scheduler")
  scheduler: DayPilotSchedulerComponent;

  events: DayPilot.EventData[];

  config: DayPilot.SchedulerConfig = {
    timeHeaders : [
      {groupBy: "Month", format: "MMMM yyyy"},
      {groupBy: "Day", format: "d"}
    ],
    days: 31,
    startDate: "2021-10-01",
    scale: "Day"
  };

  constructor(private dataService: DataServiceService) {}

  ngAfterViewInit(): void {
    this.getResources();
  }

  getResources() {
    this.dataService.getResources().subscribe(
      result => {
        this.config.resources = result;
        console.log("wa mohmaz : ", result);
      }
    );
  }

  

}
