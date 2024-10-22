import { Component, OnInit } from '@angular/core';
import { BookingService } from 'src/app/service/booking.service'; 
import { Booking } from 'src/app/models/booking.model'; 

@Component({
  selector: 'app-manage-booking',
  templateUrl: './manage-booking.component.html',
  styleUrls: ['./manage-booking.component.css']
})
export class ManageBookingComponent implements OnInit {
  bookings: Booking[] = [];
  errorMessage: string | null = null;

  constructor(private bookingService: BookingService) { }

  ngOnInit(): void {
    this.loadBookings();
  }

  loadBookings(): void {
    this.bookingService.getAllBookings().subscribe({
      next: (data) => {
        this.bookings = data;
      },
      error: (err) => {
        this.errorMessage = 'Failed to load bookings';
      }
    });
  }
    
  updateBookingStatus(bookingId: number, status: string): void {
    const updatedBooking: Booking = { bookingId, status }; // Adjust according to your Booking model
    console.log("Update booking",bookingId);
    
    this.bookingService.updateBooking(bookingId, updatedBooking).subscribe({
      next: () => {
        this.loadBookings(); // Refresh the list after updating
      },
      error: (err) => {
        this.errorMessage = 'Failed to update booking status';
      }
    });
  }
}
