import { Component, OnInit } from '@angular/core';
import { ActivatedRoute, Router } from '@angular/router';
import { Job } from 'src/app/models/job.model';
import { JobService } from 'src/app/services/job.service';

@Component({
  selector: 'app-add-job',
  templateUrl: './add-job.component.html',
  styleUrls: ['./add-job.component.css'],
})
export class AddJobComponent implements OnInit {
  job: Job = {
    jobId: 0,
    title: '',
    description: '',
    company: '',
    location: '',
  };
  successMessage: string = '';
  errorMessage: string = '';
  isEditMode: boolean = false; // To distinguish between add and update

  constructor(
    private jobService: JobService,
    private route: ActivatedRoute,
    private router: Router
  ) {}

  ngOnInit(): void {
     
    const jobId = this.route.snapshot.params['jobId'];
    if (jobId) {
      this.isEditMode = true;
      this.loadJobDetails(jobId);
    }
  }

  loadJobDetails(jobId: number): void {
    this.jobService.getJobById(jobId).subscribe({
      next: (response) => {
        this.job = response; // Populate form with fetched job details
      },
      error: (error) => {
        console.error('Error fetching job details:', error);
        this.errorMessage = 'Failed to fetch job details. Please try again.';
      },
    });
  }

  saveJob(): void {
    if (
      this.job.title &&
      this.job.description &&
      this.job.company &&
      this.job.location
    ) {
      if (this.isEditMode) {
        // Update job
        this.jobService.updateJob(this.job.jobId, this.job).subscribe({
          next: () => {
            this.successMessage = 'Job updated successfully!';
            this.router.navigate(['/jobs']); // Navigate back to the job list
          },
          error: (error) => {
            console.error('Error updating job:', error);
            this.errorMessage = 'Failed to update job. Please try again.';
          },
        });
      } else {
        // Add new job
        this.jobService.createJob(this.job).subscribe({
          next: () => {
            this.successMessage = 'Job added successfully!';
            this.clearForm();
          },
          error: (error) => {
            console.error('Error adding job:', error);
            this.errorMessage = 'Failed to add job. Please try again.';
          },
        });
      }
    } else {
      this.errorMessage = 'Please fill in all required fields.';
    }
  }

  clearForm(): void {
    this.job = {
      jobId: 0,
      title: '',
      description: '',
      company: '',
      location: '',
    };
  }
}
