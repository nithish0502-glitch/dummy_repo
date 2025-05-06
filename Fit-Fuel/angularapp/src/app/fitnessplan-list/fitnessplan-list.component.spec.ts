import { ComponentFixture, TestBed } from '@angular/core/testing';
import { FitnessListComponent } from './fitnessplan-list.component';
import { FitnessService } from '../services/fitnessplan.service';
import { of } from 'rxjs';
import { HttpClientTestingModule } from '@angular/common/http/testing';

describe('FitnessListComponent', () => {
  let component: FitnessListComponent;
  let fixture: ComponentFixture<FitnessListComponent>;
  let service: FitnessService;

  const mockFitnessPlans = [
    {
      id: 1,
      user: 'Emma Smith',
      age: 32,
      goal: 'Muscle Gain',
      workoutType: 'Strength Training',
      durationPerDay: '60 mins',
      dietPlan: 'High Protein',
      caloriesPerDay: 2500,
      contactNumber: '9876543210',
    },
    {
      id: 2,
      user: 'John Doe',
      age: 28,
      goal: 'Weight Loss',
      workoutType: 'Cardio',
      durationPerDay: '45 mins',
      dietPlan: 'Low Carb',
      caloriesPerDay: 1800,
      contactNumber: '1234567890',
    },
  ];

  beforeEach(() => {
    TestBed.configureTestingModule({
      declarations: [FitnessListComponent],
      providers: [FitnessService],
      imports: [HttpClientTestingModule],
    });

    fixture = TestBed.createComponent(FitnessListComponent);
    component = fixture.componentInstance;
    service = TestBed.inject(FitnessService);
  });

  fit('should create FitnessListComponent', () => {
    expect(component).toBeTruthy();
  });

  fit('should call getFitnessPlans', () => {
    spyOn((service as any), 'getFitnessPlans').and.returnValue(of(mockFitnessPlans));
    (component as any).getFitnessPlans();
    expect((component as any).getFitnessPlans).toBeDefined();
    expect((component as any).getFitnessPlans instanceof Function).toBeTruthy();
    expect((service as any).getFitnessPlans).toHaveBeenCalled();
  });

  fit('should call deleteFitnessPlan', () => {
    spyOn((service as any), 'deleteFitnessPlan').and.returnValue(of());
    (component as any).deleteFitnessPlan(1); // You can pass a valid fitness plan ID
    expect((component as any).deleteFitnessPlan).toBeDefined();
    expect((component as any).deleteFitnessPlan instanceof Function).toBeTruthy();
    expect((service as any).deleteFitnessPlan).toHaveBeenCalledWith(1);
  });
});
