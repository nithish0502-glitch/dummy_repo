import { Flight } from './flight.model'; // Adjust the import based on your file structure

describe('Flight Models', () => {
  fit('Frontend_day24_should_create_Flight_instance', () => {
    const flight: Flight = {
      flightNumber: 'AI202',
      airline: 'Air India',
      departureLocation: 'Delhi',
      arrivalLocation: 'Mumbai',
      departureTime: '10:00 AM',
      arrivalTime: '12:00 PM',
      price: 7500,
    };

    expect(flight).toBeTruthy();
    expect(flight.flightNumber).toBe('AI202');
    expect(flight.airline).toBe('Air India');
    expect(flight.departureLocation).toBe('Delhi');
    expect(flight.arrivalLocation).toBe('Mumbai');
    expect(flight.departureTime).toBe('10:00 AM');
    expect(flight.arrivalTime).toBe('12:00 PM');
    expect(flight.price).toBe(7500);
  });
});
