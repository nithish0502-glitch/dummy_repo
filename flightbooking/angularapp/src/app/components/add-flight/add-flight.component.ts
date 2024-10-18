import { Component, OnInit } from '@angular/core';
import { FlightService } from 'src/app/service/flight.service'; 
import { Flight } from 'src/app/models/flight.model'; 
import { Router, ActivatedRoute } from '@angular/router';

@Component({
  selector: 'app-add-flight',
  templateUrl: './add-flight.component.html',
  styleUrls: ['./add-flight.component.css']
})
export class AddFlightComponent implements OnInit {
  flight: Flight = {
    flightNumber: '',
    airline: '',
    departureLocation: '',
    arrivalLocation: '',
    departureTime: '',
    arrivalTime: '',
    price: 0
  };

  successMessage: string = '';
  errorMessage: string = '';
  showModal: boolean = false; // Controls modal visibility
  isEditing: boolean = false; // Flag to check if we are editing

  constructor(private flightService: FlightService, private router: Router, private route: ActivatedRoute) { }

  ngOnInit(): void {
    const flightId = this.route.snapshot.paramMap.get('id');
    if (flightId) {
      this.isEditing = true;
      this.loadFlight(flightId);
    }
  }

  loadFlight(id: string): void {
    this.flightService.getFlightById(+id).subscribe({
      next: (fetchedFlight) => {
        this.flight = fetchedFlight;
      },
      error: (error) => {
        this.errorMessage = 'Failed to load flight data.';
        console.error('Error loading flight:', error);
      }
    });
  }

  addOrUpdateFlight(): void {
    if (this.isEditing) {
      this.flightService.updateFlight(this.flight.flightId, this.flight).subscribe({
        next: () => {
          this.successMessage = 'Flight updated successfully!';
          this.showModal = true;
        },
        error: (error) => {
          this.errorMessage = 'Failed to update flight. Please try again.';
          console.error('Error updating flight:', error);
        }
      });
    } else {
      this.flightService.createFlight(this.flight).subscribe({
        next: () => {
          this.successMessage = 'Flight created successfully!';
          this.showModal = true;
        },
        error: (error) => {
          this.errorMessage = 'Failed to create flight. Please try again.';
          console.error('Error creating flight:', error);
        }
      });
    }
  }

  closeModal(): void {
    this.showModal = false;
    this.successMessage = ''; // Clear success message
    this.errorMessage = ''; // Clear error message
    this.router.navigate(['/flight-list']); // Navigate to flight list
  }
}
