import { AfterViewInit, Component, ViewChild } from '@angular/core';
import { DayPilot, DayPilotSchedulerComponent } from "daypilot-pro-angular";
import { DataServiceService, EventCreateParams, EventDeleteParams, EventMoveParams } from '../data-service.service';

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
    scale: "Day",
    eventDeleteHandling: "Update",
    onTimeRangeSelected: args => {
      DayPilot.Modal.prompt("New event name:", "Event").then(modal => {
        this.scheduler.control.clearSelection();
        if (!modal.result) {
          return;
        }

        let params: EventCreateParams = {
          start: args.start.toString(),
          end: args.end.toString(),
          text: modal.result,
          resource: args.resource
        };
        this.dataService.createEvent(params).subscribe(result => {
          this.events.push(result);
          this.scheduler.control.message("Event created");
        });
      });
    },
    onEventMove: args => {
        let params: EventMoveParams = {
        id: args.e.id(),
        start: args.newStart.toString(),
        end: args.newEnd.toString(),
        resource: args.newResource
      };
      this.dataService.moveEvent(params).subscribe(result => {
        this.scheduler.control.message("Event moved");
      });
    },
    onEventDelete: args => {
      let params: EventDeleteParams = {
        id: args.e.id(),
      };
      this.dataService.deleteEvent(params).subscribe(result => {
        this.scheduler.control.message("Event deleted");
      });
    },
  };

  constructor(private dataService: DataServiceService) {}

  ngAfterViewInit(): void {
    this.getResources();
    this.getEvents();
  }

  getResources() {
    this.dataService.getResources().subscribe(
      result => {
        this.config.resources = result;
        console.log("wa mohmaz : ", result);
      }
    );
  }

  getEvents() {
    var from = this.scheduler.control.visibleStart();
    var to = this.scheduler.control.visibleEnd();
    this.dataService.getEvents(from, to).subscribe(result => this.events = result);
  }

  

}
