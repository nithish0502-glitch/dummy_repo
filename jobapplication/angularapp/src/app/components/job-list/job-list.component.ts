import { Component, OnInit } from '@angular/core';
import { Router } from '@angular/router';
import { Job } from 'src/app/models/job.model';
import { JobService } from 'src/app/services/job.service';

@Component({
  selector: 'app-job-list',
  templateUrl: './job-list.component.html',
  styleUrls: ['./job-list.component.css']
})
export class JobListComponent implements OnInit {
  jobs: Job[] = [];
  errorMessage: string = '';
  isAdmin: boolean = false;
 
  constructor(private jobService: JobService, private router: Router) {}

  ngOnInit(): void {
    const userRole = localStorage.getItem('userRole');
    this.isAdmin = userRole === 'Admin';
    this.getAllJobs();
  }

  applyForJob(jobId: number): void {
    // Redirect to the JobApplicationComponent with the jobId
    this.router.navigate(['/job-application', jobId]);
  }

  navigateToUpdatePage(jobId: number): void {
    this.router.navigate(['/add-job', jobId]); // Pass jobId as a route parameter
  }
  
  getAllJobs(): void {
    this.jobService.getAllJobs().subscribe({
      next: (response) => {
        this.jobs = response;
      },
      error: (error) => {
        console.error('Error fetching jobs:', error);
        this.errorMessage = 'Failed to load jobs. Please try again.';
      }
    });
  }

  deleteJob(jobId: number): void {
    if (confirm('Are you sure you want to delete this job?')) {
      this.jobService.deleteJob(jobId).subscribe({
        next: () => {
          this.jobs = this.jobs.filter((job) => job.jobId !== jobId);
        },
        error: (error) => {
          console.error('Error deleting job:', error);
          this.errorMessage = 'Failed to delete job. Please try again.';
        }
      });
    }
  }
}
