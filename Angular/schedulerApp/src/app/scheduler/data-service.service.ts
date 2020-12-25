import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';

@Injectable()
export class DataServiceService {

  baseUrl = 'http://localhost:8080/api/resources';

  constructor(private http: HttpClient) { }

  getResources(): Observable<any> {
    return this.http.get(this.baseUrl);
  }


}
