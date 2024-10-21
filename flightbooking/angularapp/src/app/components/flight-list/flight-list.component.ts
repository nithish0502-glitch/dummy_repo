import { Component, OnInit } from '@angular/core';
import { FlightService } from 'src/app/service/flight.service'; 
import { Flight } from 'src/app/models/flight.model'; 
import { Router } from '@angular/router';

@Component({
  selector: 'app-flight-list',
  templateUrl: './flight-list.component.html',
  styleUrls: ['./flight-list.component.css']
})
export class FlightListComponent implements OnInit {
  flights: Flight[] = []; // Array to hold the list of flights
  errorMessage: string = ''; // To display any error messages
  userRole: string = localStorage.getItem('userRole');

  constructor(private flightService: FlightService, private router: Router) { }

  ngOnInit(): void {
    this.loadFlights(); // Load flights when the component initializes
    this.route.queryParams.subscribe(params => {
      this.selectedFlightId = +params['flightId'];
    });
  }

  // Method to fetch flights from the service
  loadFlights(): void {
    this.flightService.getAllFlights().subscribe({
      next: (flights) => {
        this.flights = flights; // Assign the fetched flights to the component variable
        this.errorMessage = ''; // Clear any previous error messages
      },
      error: (error) => {
        this.errorMessage = 'Failed to load flights. Please try again.'; // Set error message
        console.error('Error loading flights:', error); // Log error for debugging
      }
    });
  }

  updateFlight(id: number): void {
    console.log(id+"      nnn");
    
    this.router.navigate(['/add-flight', id]); // Navigate to add-flight with the ID
  }

  deleteFlight(id: number): void {
    this.flightService.deleteFlight(id).subscribe({
      next: () => {
        this.loadFlights(); // Reload flights after deletion
      },
      error: (error) => {
        this.errorMessage = 'Failed to delete flight. Please try again.';
        console.error('Error deleting flight:', error);
      }
    });
  }
}
