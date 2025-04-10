import { Component, OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';
import { Job } from 'src/app/models/job.model';
import { JobService } from 'src/app/services/job.service';

@Component({
  selector: 'app-application-details',
  templateUrl: './application-details.component.html',
  styleUrls: ['./application-details.component.css']
})
export class ApplicationDetailsComponent implements OnInit {
  jobId!: number;
  jobDetails: any | null = null;
  errorMessage: string = '';

  constructor(private route: ActivatedRoute, private jobService: JobService) {}

  ngOnInit(): void {
    // Retrieve the job ID from the route parameters
    this.route.params.subscribe((params) => {
      this.jobId = +params['jobId'];
      this.fetchJobById();
    });
  }

  // Fetch job details by jobId
  fetchJobById(): void {
    this.jobService.getJobById(this.jobId).subscribe(
      (data: Job) => {
        console.log('Job details:', data);
        this.jobDetails = data; // Assign the retrieved job details
      },
      (error) => {
        console.error('Error fetching job details:', error);
        this.errorMessage = 'Failed to fetch job details. Please try again later.';
      }
    );
  }
  updateApplicationStatus(application: any, newStatus: string): void {
    console.log('New Status:', newStatus);  // Add this line to log the new status
  
    if (this.jobDetails) {
      // Find the application by applicationId
      const applicationIndex = this.jobDetails.applications.findIndex((app) => app.applicationId === application.applicationId);
      
      if (applicationIndex !== -1) {
        // Update the status of the application
        this.jobDetails.applications[applicationIndex].status = newStatus;
        
        // Log the updated status in the applications array
        console.log('Updated Application Status:', this.jobDetails.applications[applicationIndex].status);
  
        // Prepare updated job details for the backend
        const updatedJob = {
          ...this.jobDetails, // Spread existing job details
          applications: this.jobDetails.applications // Ensure updated applications array
        };
  
        // Send the updated job details to the backend
        this.jobService.updateJob(this.jobDetails.jobId, updatedJob).subscribe(
          (updatedJobData) => {
            console.log('Job updated successfully:', updatedJobData);
          },
          (error) => {
            console.error('Error updating job:', error);
            this.errorMessage = 'Failed to update job details. Please try again later.';
          }
        );
      }
    }
  }
  
  
}
