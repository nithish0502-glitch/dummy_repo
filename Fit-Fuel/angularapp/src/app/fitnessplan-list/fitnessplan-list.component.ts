import { Component, OnInit } from '@angular/core';
import { FitnessPlan } from '../model/fitnessplan.model'; 
import { FitnessService } from '../services/fitnessplan.service';

@Component({
  selector: 'app-fitness-list',
  templateUrl: './fitnessplan-list.component.html',
  styleUrls: ['./fitnessplan-list.component.css']
})
export class FitnessListComponent implements OnInit {
  fitnessPlans: FitnessPlan[] = [];
  isAscending: boolean = true;

  constructor(private fitnessService: FitnessService) { }

  ngOnInit(): void {
    this.getFitnessPlans();
  }

  getFitnessPlans(): void {
    this.fitnessService.getPlans()
      .subscribe(
        (res) => {
          console.log(res);
          this.fitnessPlans = res;
        },
        (err) => {
          console.error('Error fetching fitness plans:', err);
        }
      );
  }

  deleteFitnessPlan(id: any): void {
    this.fitnessService.deletePlan(id)
      .subscribe(
        () => {
          // Remove the deleted fitness plan from the list
          this.fitnessPlans = this.fitnessPlans.filter(plan => plan.id !== id);
        },
        (err) => {
          console.error('Error deleting fitness plan:', err);
        }
      );
  }

  sortByCalories(): void {
    if (this.isAscending) {
      this.fitnessPlans.sort((a, b) => a.caloriesPerDay - b.caloriesPerDay);
    } else {
      this.fitnessPlans.sort((a, b) => b.caloriesPerDay - a.caloriesPerDay);
    }
    this.isAscending = !this.isAscending;
  }
}

