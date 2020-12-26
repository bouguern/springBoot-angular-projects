import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { DayPilot } from 'daypilot-pro-angular';
import { Observable } from 'rxjs';

@Injectable()
export class DataServiceService {

  baseUrl = "http://localhost:8080/api";

  constructor(private http: HttpClient) { }

  getResources(): Observable<any> {
    return this.http.get(this.baseUrl + "/resources");
  }

  getEvents(from: DayPilot.Date, to: DayPilot.Date): Observable<any[]> {
    return this.http.get(this.baseUrl + "/events?from=" + from.toString() + "&to=" + to.toString()) as Observable<any>;
  }

  createEvent(data: EventCreateParams): Observable<EventData> {
    return this.http.post(this.baseUrl + "/events/create", data) as Observable<any>;
  }

  moveEvent(data: EventMoveParams): Observable<EventData> {
    return this.http.post(this.baseUrl + "/events/move", data) as Observable<any>;
  }

  deleteEvent(data: EventDeleteParams):Observable<EventData> {
    return this.http.post(this.baseUrl + "/events/delete", data) as Observable<any>;
  }


}

export interface EventCreateParams {
  start: string;
  end: string;
  text: string;
  resource: string | number;
}

export interface EventData {
  id: string | number;
  start: string;
  end: string;
  text: string;
  resource: string | number;
}

export interface EventMoveParams {
  id: string | number;
  start: string;
  end: string;
  resource: string | number;
}

export interface EventDeleteParams {
  id: string | number;
}