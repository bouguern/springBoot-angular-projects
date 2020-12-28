import { Component, OnInit, ViewChild } from '@angular/core';
import { GoogleMap } from '@angular/google-maps';

@Component({
  selector: 'app-root',
  templateUrl: './app.component.html',
  styleUrls: ['./app.component.css']
})
export class AppComponent implements OnInit {

  @ViewChild(GoogleMap, { static: false }) map: GoogleMap;
  
  title = 'angularGoogleMaps';

  zoom = 12;
  center: google.maps.LatLngLiteral;
  options: google.maps.MapOptions = {
    mapTypeId: 'roadmap', // displays the default road map view. This is the default map type.
    zoomControl: true, // allow zooming the map.
    scrollwheel: false,
    disableDoubleClickZoom: true,
    maxZoom: 20,
    minZoom: 8,
  };

  markers = [];

  ngOnInit() {
    navigator.geolocation.getCurrentPosition((position) => { // get current localisation
      this.center = {
        lat: position.coords.latitude,
        lng: position.coords.longitude,
      };
      this.markers.push({ // put the marker
        position: {
          lat: this.center.lat,
          lng: this.center.lng,
        },
        label: {
          color: 'black',
          text: 'Marker label ' + (this.markers.length + 1),
        },
        title: 'Marker title ' + (this.markers.length + 1),
        options: { animation: google.maps.Animation.BOUNCE, },
      });
    });

    
  }

  // print latitude and longitude on the console.
  click(event: google.maps.MapMouseEvent) {
    console.log(event);
  }


  // adding a marker near to my localisation.
  addMarker() {
    this.markers.push({
      position: {
        lat: this.center.lat + ((Math.random() - 0.5) * 2) / 10,
        lng: this.center.lng + ((Math.random() - 0.5) * 2) / 10,
      },
      label: {
        color: 'red',
        text: 'Marker label ' + (this.markers.length + 1),
      },
      title: 'Marker title ' + (this.markers.length + 1),
      options: { animation: google.maps.Animation.BOUNCE, },
    });
  }

  // print my position on the console.
  logCenter() {
    console.log(JSON.stringify(this.map.getCenter()));
  }

}
