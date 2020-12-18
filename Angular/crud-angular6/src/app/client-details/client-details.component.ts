import { Component, Input, OnInit } from '@angular/core';
import { ClientListComponent } from '../client-list/client-list.component';
import { Client } from '../model/client';
import { ClientService } from '../services/client.service';

@Component({
  selector: 'app-client-details',
  templateUrl: './client-details.component.html',
  styleUrls: ['./client-details.component.css']
})
export class ClientDetailsComponent implements OnInit {


  @Input() client: Client;


  constructor(private clientService: ClientService, private listComponent: ClientListComponent) { }

  ngOnInit() {
  }

  updateAvailable(isAvailable: boolean) {
    this.clientService.updateClient(this.client.id, {
      name: this.client.name,
      age: this.client.age,
      available: isAvailable
    }).subscribe(
      data => {
        console.log(data);
        this.client = data as Client;
      },
      error => {
        console.log(error);
      }
    );
  }

  deleteClient() {
    this.clientService.deleteClient(this.client.id).subscribe(
      data => {
        console.log(data);
        this.listComponent.reloadData();
      },
      error => {
        console.log(error);
      }
    );
  }



}
