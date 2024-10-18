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
    flightId: 0,
    flightNumber: '',
    airline: '',
    departureLocation: '',
    arrivalLocation: '',
    departureTime: new Date(),
    arrivalTime: new Date(),
    price: 0
  };

  successMessage: string = '';
  errorMessage: string = '';
  showModal: boolean = false; // To control modal visibility

  constructor(private flightService: FlightService, private router: Router) { }

  ngOnInit(): void { }

  addFlight(): void {
    this.flightService.createFlight(this.flight).subscribe({
      next: (createdFlight) => {
        this.successMessage = 'Flight created successfully!';
        this.errorMessage = ''; 
        this.showModal = true; // Show the modal
      },
      error: (error) => {
        this.errorMessage = 'Failed to create flight. Please try again.';
        this.successMessage = '';
        console.error('Error creating flight:', error);
      }
    });
  }

  closeModal(): void {
    this.showModal = false; // Hide the modal
    this.router.navigate(['/flights']); // Navigate to flight list
  }
}
