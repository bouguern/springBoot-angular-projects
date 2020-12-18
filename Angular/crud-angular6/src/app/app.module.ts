import { BrowserModule } from '@angular/platform-browser';
import { NgModule } from '@angular/core';
import { FormsModule } from '@angular/forms';

import { AppComponent } from './app.component';
import { CreateClientComponent } from './create-client/create-client.component';
import { ClientDetailsComponent } from './client-details/client-details.component';
import { ClientListComponent } from './client-list/client-list.component';
import { SearchClientComponent } from './search-client/search-client.component';
import { HttpClientModule } from '@angular/common/http';

import { RouterModule } from '@angular/router';


@NgModule({
  declarations: [
    AppComponent,
    CreateClientComponent,
    ClientDetailsComponent,
    ClientListComponent,
    SearchClientComponent
  ],
  imports: [
    BrowserModule,
    FormsModule,
    HttpClientModule,
    RouterModule.forRoot([
      { path: '', redirectTo: 'client', pathMatch: 'full' },
      { path: 'client', component: ClientListComponent },
      { path: 'add', component: CreateClientComponent },
      { path: 'findbyage', component: SearchClientComponent },
    ])
  ],
  providers: [],
  bootstrap: [AppComponent]
})
export class AppModule { }
