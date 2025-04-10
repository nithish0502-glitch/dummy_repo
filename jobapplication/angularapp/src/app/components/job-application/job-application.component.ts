import { Component, OnInit } from '@angular/core';
import { ApplicationService } from 'src/app/services/application.service'; 
import { Application } from 'src/app/models/application.model'; 
import { ActivatedRoute } from '@angular/router';
import { HttpErrorResponse } from '@angular/common/http'; // Import HttpErrorResponse

@Component({
  selector: 'app-job-application',
  templateUrl: './job-application.component.html',
  styleUrls: ['./job-application.component.css'],
})
export class JobApplicationComponent implements OnInit {
  applications: Application[] = [];
  selectedApplication: Application | null = null;
  newApplication: Application = {
    applicationId: 0,
    status: '',
    userId: 0,
    jobId: 0,
    yearsOfExperience: 0,
    skills: '',
    applicationDate: new Date(),
    locationPreference: '',
  };
  errorMessage: string = ''; // To hold the error message

  constructor(private applicationService: ApplicationService, private route: ActivatedRoute) {}

  ngOnInit(): void {
    // Get the jobId from the route parameters
    this.route.paramMap.subscribe((params) => {
      this.newApplication.jobId = +params.get('jobId')!; // Get jobId from route and assign it
    });

    // Retrieve userId from localStorage
    const userId = localStorage.getItem('userId');
    if (userId) {
      this.newApplication.userId = +userId;  // Set the userId from localStorage
    }
  }

  selectApplication(application: Application): void {
    this.selectedApplication = application;
  }

  addApplication(): void {
    // Prepare the payload with the correct structure
    const applicationPayload = {
      job: {
        jobId: this.newApplication.jobId,  // Get jobId from route parameters
      },
      user: {
        userId: this.newApplication.userId,  // Get userId from localStorage
      },
      // status: this.newApplication.status,
      status: "Pending",
      yearsOfExperience: this.newApplication.yearsOfExperience,
      skills: this.newApplication.skills,
      applicationDate: this.newApplication.applicationDate,
      locationPreference: this.newApplication.locationPreference,
    };
  
    // Call the service to save the application with the prepared payload
    this.applicationService.saveApplication(applicationPayload).subscribe(
      (application: Application) => {
        // On success, add the application to the list and reset the form
        this.applications.push(application);
        this.newApplication = {
          applicationId: 0,
          status: '',
          userId: 0,
          jobId: 0,
          yearsOfExperience: 0,
          skills: '',
          applicationDate: new Date(),
          locationPreference: '',
        };
        this.errorMessage = ''; // Clear any previous error messages
      },
      (error: HttpErrorResponse) => {
        // Handle error - check for specific HTTP status code
        if (error.status === 400) {
          this.errorMessage = "You have already applied for this job.";
        } else {
          this.errorMessage = 'An error occurred while saving your application. Please try again later.';
        }
        console.error('Error saving application', error);
      }
    );
  }
}
