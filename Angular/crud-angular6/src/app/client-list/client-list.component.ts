import { Component, OnInit } from '@angular/core';
import { Observable } from 'rxjs';
import { Client } from '../model/client';
import { ClientService } from '../services/client.service';

@Component({
  selector: 'app-client-list',
  templateUrl: './client-list.component.html',
  styleUrls: ['./client-list.component.css']
})
export class ClientListComponent implements OnInit {


clients: Observable<Client[]>;

  constructor(private clientService: ClientService) { }

  ngOnInit() {
    this.reloadData();
  }

  deleteClients() {
    this.clientService.deleteAll().subscribe(
      data => {
        console.log(data);
        this.reloadData();
      },
      error => {
        console.log('Error: ' +error);
      }
    );
  }

  reloadData() {
    this.clients = this.clientService.getClientsList();
  }

}
