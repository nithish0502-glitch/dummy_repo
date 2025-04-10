import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Observable } from 'rxjs';
import { Job } from '../models/job.model';
import { apiUrl } from 'src/apiconfig'; // Import your API base URL

@Injectable({
  providedIn: 'root',
})
export class JobService {
  private endpoint = `${apiUrl}/api/jobs`; // Concatenate the base URL with the jobs endpoint

  constructor(private http: HttpClient) {}

  createJob(job: Job): Observable<Job> {
    return this.http.post<Job>(this.endpoint, job);
  }

  updateJob(id: number, job: Job): Observable<Job> {
    return this.http.put<Job>(`${this.endpoint}/${id}`, job);
  }

  getJobsByUserId(userId: number): Observable<Job[]> {
    return this.http.get<Job[]>(`${this.endpoint}/user/${userId}`);
  }

  getJobById(id: number): Observable<Job> {
    return this.http.get<Job>(`${this.endpoint}/${id}`);
  }

  getAllJobs(): Observable<Job[]> {
    return this.http.get<Job[]>(this.endpoint);
  }

  deleteJob(id: number): Observable<void> {
    return this.http.delete<void>(`${this.endpoint}/${id}`);
  }
}
