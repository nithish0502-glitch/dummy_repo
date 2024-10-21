import { Component, OnInit } from '@angular/core';
import { BookingService } from 'src/app/service/booking.service'; 
import { FlightService } from 'src/app/service/flight.service';
import { Flight } from 'src/app/models/flight.model'; 
import { ActivatedRoute } from '@angular/router'; 

@Component({
  selector: 'app-booking-form',
  templateUrl: './booking-form.component.html',
  styleUrls: ['./booking-form.component.css']
})
export class BookingFormComponent implements OnInit {

  flights: Flight[] = [];
  selectedFlightId: number | null = null;
  numberOfPassengers: number = 1;
  errorMessage: string | null = null;
  successMessage: string | null = null;

  constructor(private bookingService: BookingService, private flightService: FlightService, private route: ActivatedRoute) { }

  ngOnInit(): void {
    // Get the flightId from query parameters
    this.route.queryParams.subscribe(params => {
      this.selectedFlightId = +params['flightId'];
      console.log("Selected Flight ID:", this.selectedFlightId); // Log the flight ID

      // Load flights
      this.loadAvailableFlights();
    });
  }

  loadAvailableFlights(): void {
    this.flightService.getAllFlights().subscribe({
      next: (data) => {
        this.flights = data;
      },
      error: (err) => {
        this.errorMessage = 'Failed to load flights';
      }
    });
  }

  bookFlight(): void {
    if (this.selectedFlightId && this.numberOfPassengers > 0) {
      const newBooking ={
        flight: {
          flightId: this.selectedFlightId
        },
        user: {
          userId: parseInt(localStorage.getItem('userId') || '0', 10)
        },
        bookingDate: new Date(),
        numberOfPassengers: this.numberOfPassengers,
        status: 'CONFIRMED'
      };

      this.bookingService.createBooking(newBooking).subscribe({
        next: () => {
          this.successMessage = 'Booking confirmed successfully!';
          this.errorMessage = null;
          // this.resetForm();
        },
        error: (err) => {
          this.errorMessage = 'Failed to confirm booking';
          this.successMessage = null;
        }
      });
    } else {
      this.errorMessage = 'Please select a flight and enter a valid number of passengers.';
    }
  }

  getSelectedFlightDetails(): string {
    if (!this.selectedFlightId) {
        console.log("No flight selected.");
        return 'No flight selected';
    }

    const flight = this.flights.find(f => f.flightId === this.selectedFlightId);
    
    if (!flight) {
        console.log("Flight not found for ID:", this.selectedFlightId);
        return 'Flight not found';
    }
    console.log("Flight number selected", flight.flightNumber);
    
    return `${flight.flightNumber} - ${flight.airline}`;
}

}
