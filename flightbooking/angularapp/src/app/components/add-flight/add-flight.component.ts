import { Component, OnInit } from '@angular/core';
import { FlightService } from 'src/app/service/flight.service'; 
import { Flight } from 'src/app/models/flight.model'; 
import { Router } from '@angular/router';

@Component({
  selector: 'app-add-flight',
  templateUrl: './add-flight.component.html',
  styleUrls: ['./add-flight.component.css']
})
export class AddFlightComponent implements OnInit {
  flight: Flight = {
    flightId: 0, // Initialize flightId if needed
    flightNumber: '',
    airline: '',
    departureLocation: '',
    arrivalLocation: '',
    departureTime: new Date(), // Initialize with the current date/time
    arrivalTime: new Date(),   // Initialize with the current date/time
    price: 0
  };
  
  successMessage: string = '';
  errorMessage: string = '';

  constructor(private flightService: FlightService, private router: Router) { }

  ngOnInit(): void {
  }

  // Method to handle flight submission
  addFlight(): void {
    // Ensure to send the Date objects as they are
    this.flightService.createFlight(this.flight).subscribe({
      next: (createdFlight) => {
        this.successMessage = 'Flight created successfully!';
        this.errorMessage = ''; // Clear any previous error messages
        this.router.navigate(['/flights']); // Adjust the route as necessary
      },
      error: (error) => {
        this.errorMessage = 'Failed to create flight. Please try again.';
        this.successMessage = ''; // Clear any previous success messages
        console.error('Error creating flight:', error);
      }
    });
  }
}
