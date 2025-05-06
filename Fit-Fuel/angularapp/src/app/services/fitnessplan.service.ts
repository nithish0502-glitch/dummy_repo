import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Observable } from 'rxjs';
import { FitnessPlan } from '../model/fitnessplan.model'; 

@Injectable({
  providedIn: 'root'
})
export class FitnessService {
  addFitnessPlan(value: any) {
    throw new Error('Method not implemented.');
  }
  // Replace this URL with your actual backend endpoint
  private backendUrl = 'https://ide-fddbbedbb327214235bfdebbddcaecone.premiumproject.examly.io/proxy/3001/plans';

  constructor(private http: HttpClient) { }

  // Get all fitness plans
  getPlans(): Observable<FitnessPlan[]> {
    return this.http.get<FitnessPlan[]>(this.backendUrl);
  }

  // Add a new fitness plan
  addPlan(plan: FitnessPlan): Observable<FitnessPlan> {
    return this.http.post<FitnessPlan>(this.backendUrl, plan);
  }

  // Delete a fitness plan by ID
  deletePlan(id: number): Observable<void> {
    const url = `${this.backendUrl}/${id}`;
    return this.http.delete<void>(url);
  }
}
