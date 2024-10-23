import { Booking } from './booking.model'; // Adjust the import based on your file structure

describe('Booking Models', () => {
  fit('Frontend_day24_should_create_Booking_instance', () => {
    const booking: Booking = {
      numberOfPassengers: 2,
      status: 'CONFIRMED',
    };

    expect(booking).toBeTruthy();
    expect(booking.numberOfPassengers).toBe(2);
    expect(booking.status).toBe('CONFIRMED');
  });
});
