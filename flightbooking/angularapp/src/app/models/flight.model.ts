export interface Flight {
    flightId?: number;
    flightNumber: string;
    airline: string;
    departureLocation: string;
    arrivalLocation: string;
    departureTime: string; // Change to string for time only
    arrivalTime: string;   // Change to string for time only
    price: number;
}
