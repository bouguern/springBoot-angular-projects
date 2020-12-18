import { Component, OnInit } from '@angular/core';
import { Client } from '../model/client';
import { ClientService } from '../services/client.service';

@Component({
  selector: 'app-search-client',
  templateUrl: './search-client.component.html',
  styleUrls: ['./search-client.component.css']
})
export class SearchClientComponent implements OnInit {


  age: number;
  clients: Client[];

  constructor(private dataService: ClientService) { }

  ngOnInit() {
    this.age = 0;
  }

  private searchClients() {
    this.dataService.getClientsByAge(this.age)
      .subscribe(clients => this.clients = clients);
  }
 
  onSubmit() {
    this.searchClients();
  }

}
