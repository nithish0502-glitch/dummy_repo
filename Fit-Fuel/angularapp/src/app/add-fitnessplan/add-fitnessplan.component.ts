import { Component, OnInit } from '@angular/core';
import { FormBuilder, FormGroup, Validators } from '@angular/forms';
import { Router } from '@angular/router';
import { FitnessService } from '../services/fitnessplan.service';

@Component({
  selector: 'app-add-fitnessplan',
  templateUrl: './add-fitnessplan.component.html',
  styleUrls: ['./add-fitnessplan.component.css']
})
export class AddFitnessPlanComponent implements OnInit {

  fitnessForm: FormGroup;

  constructor(
    private formBuilder: FormBuilder,
    private fitnessService: FitnessService,
    private router: Router
  ) {
    this.fitnessForm = this.formBuilder.group({
      user: ['', Validators.required],
      age: ['', Validators.required],
      goal: ['', Validators.required],
      workoutType: ['', Validators.required],
      durationPerDay: ['', Validators.required],
      dietPlan: ['', Validators.required],
      caloriesPerDay: ['', Validators.required],
      contactNumber: ['', [Validators.required, Validators.pattern('^[0-9]{10}$')]]
    });
  }

  ngOnInit(): void {}

  addNewPlan() {
    if (this.fitnessForm.valid) {
      console.log(this.fitnessForm.value);
      this.fitnessService.addPlan(this.fitnessForm.value).subscribe(
        (res) => {
          console.log(res);
          this.router.navigateByUrl('/plans');
        },
        (err) => {
          console.error(err);
        }
      );
    }
  }
}
