import { Component, OnInit } from '@angular/core';
import { JobService } from 'src/app/services/job.service';
import { Job } from 'src/app/models/job.model';

@Component({
  selector: 'app-my-application',
  templateUrl: './my-application.component.html',
  styleUrls: ['./my-application.component.css'],
})   
export class MyApplicationComponent implements OnInit {
  jobs: Job[] = []; // Array to hold jobs with applications
  errorMessage: string = ''; // To display error messages if any
  currenUserId:any;

  constructor(private jobService: JobService) {}

  ngOnInit(): void {
    // Retrieve userId from localStorage
    const storedUserId = localStorage.getItem('userId');
    if (storedUserId) {
      const userId = +storedUserId; // Convert userId to a number
      this.currenUserId=userId;
      this.fetchJobsByUserId(userId);
    } else {
      this.errorMessage = 'User ID not found in localStorage.';
    }
  }

  fetchJobsByUserId(userId: number): void {
    
    this.jobService.getJobsByUserId(userId).subscribe(
      (data: Job[]) => {

        console.log('Fetch Jobs by User Id', data);
        this.jobs = data; // Assign retrieved jobs to the array
      },
      (error) => {
        console.error('Error fetching jobs:', error);
        this.errorMessage = 'Failed to fetch jobs. Please try again later.';
      }
    );
  }
}
