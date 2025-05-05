import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Observable } from 'rxjs';
import { FitnessPlan } from '../model/fp.model';

@Injectable({
  providedIn: 'root'
})
export class FitnessService {
  private backendUrl = 'https://ide-fddbbedbb327214235bfdebbddcaecone.premiumproject.examly.io/proxy/3001/'; // Replace with your actual backend URL

  constructor(private http: HttpClient) { }

  getPlans(): Observable<FitnessPlan[]> {
    return this.http.get<FitnessPlan[]>(this.backendUrl);
  }

  addPlan(plan: FitnessPlan): Observable<FitnessPlan> {
    return this.http.post<FitnessPlan>(this.backendUrl, plan);
  }

  deletePlan(id: number): Observable<void> {
    const url = `${this.backendUrl}/${id}`;
    return this.http.delete<void>(url);
  }
}