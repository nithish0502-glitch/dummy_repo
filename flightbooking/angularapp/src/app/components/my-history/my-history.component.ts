import { Component, OnInit } from '@angular/core';
import { BookingService } from 'src/app/service/booking.service';
import { Booking } from 'src/app/models/booking.model'; 

@Component({
  selector: 'app-my-history',
  templateUrl: './my-history.component.html',
  styleUrls: ['./my-history.component.css']
})
export class MyHistoryComponent implements OnInit {
  bookings: Booking[] = [];
  userId: number = parseInt(localStorage.getItem('userId') || '0', 10);

  constructor(private bookingService: BookingService) { }

  ngOnInit(): void {
    this.loadUserBookings();
  }

  loadUserBookings(): void {
    this.bookingService.getBookingsByUserId(this.userId).subscribe(
      (data) => {
        this.bookings = data;
      },
      (error) => {
        console.error('Error fetching bookings', error);
      }
    );
  }
}
