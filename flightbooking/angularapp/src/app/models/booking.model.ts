
export interface Booking {
    bookingId?: number;
    flightId?: number; // Reference to Flight model
    userId?: number;     // Reference to User model
    bookingDate?: Date;
    numberOfPassengers?: number;
    status?: string; // e.g., CONFIRMED, CANCELED
}