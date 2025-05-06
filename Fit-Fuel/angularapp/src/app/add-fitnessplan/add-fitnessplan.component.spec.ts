import { ComponentFixture, TestBed } from '@angular/core/testing';
import { ReactiveFormsModule, FormBuilder, Validators } from '@angular/forms';
import { AddFitnessPlanComponent } from './add-fitnessplan.component';
import { FitnessService } from '../services/fitnessplan.service';
import { of } from 'rxjs';
import { Router } from '@angular/router';
import { DebugElement } from '@angular/core';
import { HttpClientTestingModule } from '@angular/common/http/testing';
import { RouterTestingModule } from '@angular/router/testing';

describe('AddFitnessPlanComponent', () => {
  let component: AddFitnessPlanComponent;
  let fixture: ComponentFixture<AddFitnessPlanComponent>;
  let service: FitnessService;
  let debugElement: DebugElement;
  let formBuilder: FormBuilder;
  let router: Router;

  beforeEach(() => {
    TestBed.configureTestingModule({
      declarations: [AddFitnessPlanComponent],
      imports: [ReactiveFormsModule, HttpClientTestingModule, RouterTestingModule],
      providers: [FitnessService],
    });

    formBuilder = TestBed.inject(FormBuilder);
    fixture = TestBed.createComponent(AddFitnessPlanComponent);
    component = fixture.componentInstance;
    service = TestBed.inject(FitnessService);
    fixture.detectChanges();
    router = TestBed.inject(Router);
    spyOn(router, 'navigateByUrl').and.returnValue(Promise.resolve(true));
  });

  fit('should create AddFitnessPlanComponent', () => {
    expect(component).toBeTruthy();
  });

  fit('should add a new fitness plan when form is valid', () => {
    const mockPlan = {
      user: 'Emma Smith',
      age: 32,
      goal: 'Muscle Gain',
      workoutType: 'Strength Training',
      durationPerDay: '60 mins',
      dietPlan: 'High Protein',
      caloriesPerDay: 2500,
      contactNumber: '9876543210'
    };

    spyOn(service as any, 'addPlan').and.returnValue(of(mockPlan));

    (component as any).fitnessForm.setValue(mockPlan);
    (component as any).addNewPlan();

    expect((component as any).fitnessForm.valid).toBeTruthy();
    expect((service as any).addPlan).toHaveBeenCalledWith(mockPlan);
    expect(router.navigateByUrl).toHaveBeenCalledWith('/plans');
  });

  fit('should require all form fields to be filled in', () => {
    const form = component.fitnessForm;

    form.setValue({
      user: '',
      age: '',
      goal: '',
      workoutType: '',
      durationPerDay: '',
      dietPlan: '',
      caloriesPerDay: '',
      contactNumber: ''
    });

    expect(form.valid).toBeFalsy();
    expect(form.get('user')?.hasError('required')).toBeTruthy();
    expect(form.get('age')?.hasError('required')).toBeTruthy();
    expect(form.get('goal')?.hasError('required')).toBeTruthy();
    expect(form.get('workoutType')?.hasError('required')).toBeTruthy();
    expect(form.get('durationPerDay')?.hasError('required')).toBeTruthy();
    expect(form.get('dietPlan')?.hasError('required')).toBeTruthy();
    expect(form.get('caloriesPerDay')?.hasError('required')).toBeTruthy();
    expect(form.get('contactNumber')?.hasError('required')).toBeTruthy();
  });

  fit('should validate contact number format', () => {
    const form = component.fitnessForm;

    form.setValue({
      user: 'Emma Smith',
      age: 32,
      goal: 'Muscle Gain',
      workoutType: 'Strength Training',
      durationPerDay: '60 mins',
      dietPlan: 'High Protein',
      caloriesPerDay: 2500,
      contactNumber: '9876543210' // Valid
    });

    expect(form.valid).toBeTruthy();
    expect(form.get('contactNumber')?.hasError('pattern')).toBeFalsy();

    form.setValue({
      user: 'Emma Smith',
      age: 32,
      goal: 'Muscle Gain',
      workoutType: 'Strength Training',
      durationPerDay: '60 mins',
      dietPlan: 'High Protein',
      caloriesPerDay: 2500,
      contactNumber: '12345' // Invalid
    });

    expect(form.invalid).toBeTruthy();
    expect(form.get('contactNumber')?.hasError('pattern')).toBeTruthy();
  });
});
