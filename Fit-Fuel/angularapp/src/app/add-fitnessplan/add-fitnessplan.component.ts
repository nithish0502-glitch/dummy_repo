import { Component, OnInit } from '@angular/core';
import { FormBuilder, FormGroup, Validators } from '@angular/forms';
import { Router } from '@angular/router';
import { FitnessService } from '../services/fitnessplan.service';

@Component({
  selector: 'app-add-fitness-plan',
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
      planName: ['', Validators.required],
      durationInWeeks: ['', [Validators.required, Validators.min(1)]],
      intensityLevel: ['', Validators.required], // e.g., Low, Medium, High
      description: ['', Validators.required],
      price: ['', [Validators.required, Validators.min(0)]],
      contactEmail: ['', [Validators.required, Validators.email]],
    });
  }

  ngOnInit(): void {}

  addNewPlan() {
    if (this.fitnessForm.valid) {
      console.log(this.fitnessForm.value);
      this.fitnessService.addPlan(this.fitnessForm.value).subscribe(
        (res) => {
          console.log(res);
          this.router.navigateByUrl('/fitness-plans');
        },
        (err) => {
          console.error(err);
        }
      );
    }
  }
}

