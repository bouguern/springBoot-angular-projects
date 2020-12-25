import { Component, OnInit } from '@angular/core';

import * as CanvasJS from '../assets/canvasjs.min';

@Component({
  selector: 'app-root',
  templateUrl: './app.component.html',
  styleUrls: ['./app.component.css']
})
export class AppComponent implements OnInit{
  
  title = 'Charts & Graphs in Angular';

  ngOnInit() {
    this.columnChart();
  }

  columnChart() {
    let chart = new CanvasJS.Chart("chartContainer1", {
      animationEnabled: true,
      exportEnabled: true,
      title: {
        text: "Column Chart"
      },
      data: [{
        type: "column",
        dataPoints: [
          { y: 30, label: "Lychee"},
          { y: 14, label: "Jackfruit"},
          { y: 119, label: "Grapes"},
          { y: 95, label: "Orange"},
          { y: 68, label: "Banana"},
          { y: 50, label: "Apple"},
          { y: 100, label: "Mango"},
          { y: 55, label: "Pears"},
          { y: 30, label: "Pineapple"}
        ]
      }]
    });
    chart.render();
  }

  

}
