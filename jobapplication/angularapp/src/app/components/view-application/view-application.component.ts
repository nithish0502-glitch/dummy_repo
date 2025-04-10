import { Component, OnInit } from '@angular/core';
import { JobService } from 'src/app/services/job.service';
import { Job } from 'src/app/models/job.model';
import { Router } from '@angular/router';

@Component({
  selector: 'app-view-application',
  templateUrl: './view-application.component.html',
  styleUrls: ['./view-application.component.css']
})
export class ViewApplicationComponent implements OnInit {
  jobs: Job[] = []; // Array to store jobs
  errorMessage: string = ''; // To display error messages if any

  constructor(private jobService: JobService, private router: Router) {}

  ngOnInit(): void {
    // Retrieve jobs on component initialization
    this.fetchJobs();
  }

  fetchJobs(): void {
    this.jobService.getAllJobs().subscribe(
      (data: Job[]) => {
        console.log("Jobs data:", data);
        this.jobs = data; // Assign retrieved jobs to the array
      },
      (error) => {
        console.error('Error fetching jobs:', error);
        this.errorMessage = 'Failed to fetch jobs. Please try again later.';
      }
    );
  }

  viewJobDetails(jobId: number): void {
    this.router.navigate(['/job-details', jobId]);
  }
}
