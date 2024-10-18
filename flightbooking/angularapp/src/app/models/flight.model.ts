export interface Flight {
    flightId?: number;
    flightNumber: string;
    airline: string;
    departureLocation: string;
    arrivalLocation: string;
    departureTime: Date; // Use Date type
    arrivalTime: Date;   // Use Date type
    price: number;
}
