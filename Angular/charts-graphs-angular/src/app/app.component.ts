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
    this.performanceChart();
    this.roundChart();
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

  performanceChart() {
    let dataPoints = [];
	  let y = 0;		
	  for ( var i = 0; i < 10000; i++ ) {		  
		  y += Math.round(5 + Math.random() * (-5 - 5));	
		  dataPoints.push({ y: y});
    }

	  let chart = new CanvasJS.Chart("chartContainer2", {
		  zoomEnabled: true,
		  animationEnabled: true,
		  exportEnabled: true,
		  title: {
			  text: "Performance Demo - 10000 DataPoints"
		  },
		  subtitles: [{
			  text: "Try Zooming and Panning"
		  }],
		  data: [
		    {
			    type: "line",                
			    dataPoints: dataPoints
		    }]
	  });
		
	  chart.render();
  }

  roundChart() {
    let chart = new CanvasJS.Chart("chartContainer3", {
      theme: "light2",
      animationEnabled: true,
      exportEnabled: true,
      title: {
        text: "Monthly Expense"
      },
      data: [{
        type: "pie",
        showInLegend: true,
        toolTipContent: "<b>{name}</b>: ${y} (#percent%)",
        indexLabel: "{name} - #percent%",
        dataPoints: [
          { y: 150, name: "Others"},
          { y: 260, name: "Education"},
          { y: 170, name: "Housing"},
          { y: 100, name: "Traveling"},
          { y: 200, name: "Shopping"},
          { y: 130, name: "Insurance"},
          { y: 300, name: "Food"}
        ]
      }]
    });
    chart.render();
  }


}
