import { Injectable } from '@angular/core';
import { HttpClient, HttpHeaders } from '@angular/common/http';
import { Observable } from 'rxjs';
import { Flight } from '../models/flight.model';
import { apiUrl } from '../apiconfig'; 

@Injectable({
  providedIn: 'root'
})
export class FlightService {
  public baseUrl = apiUrl;
 // Replace with your backend URL

 public apiUrl = `${this.baseUrl}/api/flights`;

  constructor(private http: HttpClient) { }

  // Create a new flight
  createFlight(flight: Flight): Observable<Flight> {
    return this.http.post<Flight>(this.apiUrl, flight, this.httpOptions);
  }

  // Get flight by ID
  getFlightById(id: number): Observable<Flight> {
    return this.http.get<Flight>(`${this.apiUrl}/${id}`);
  }

  // Get all flights
  getAllFlights(): Observable<Flight[]> {
    return this.http.get<Flight[]>(this.apiUrl);
  }

  // Update an existing flight
  updateFlight(id: number, flight: Flight): Observable<Flight> {
    return this.http.put<Flight>(`${this.apiUrl}/${id}`, flight, this.httpOptions);
  }

  // Delete a flight
  deleteFlight(id: number): Observable<void> {
    return this.http.delete<void>(`${this.apiUrl}/${id}`);
  }

  // Define common HTTP headers
  private httpOptions = {
    headers: new HttpHeaders({
      'Content-Type': 'application/json'
    })
  };
}
