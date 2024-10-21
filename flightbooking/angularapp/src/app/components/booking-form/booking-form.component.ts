import { Component, OnInit } from '@angular/core';
import { BookingService } from 'src/app/service/booking.service'; 
import { FlightService } from 'src/app/service/flight.service'; 
import { Booking } from 'src/app/models/booking.model'; 
import { Flight } from 'src/app/models/flight.model'; 

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

  constructor(private bookingService: BookingService, private flightService: FlightService) { }

  ngOnInit(): void {
    this.loadAvailableFlights();
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
      const newBooking = {
        flight: {
          flightId: this.selectedFlightId
      },
      user:{
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
          this.resetForm();
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

  resetForm(): void {
    this.selectedFlightId = null;
    this.numberOfPassengers = 1;
  }
}
