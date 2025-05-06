import { TestBed } from '@angular/core/testing';
import { HttpClientTestingModule, HttpTestingController } from '@angular/common/http/testing';
import { FitnessService } from './fitnessplan.service';
import { FitnessPlan } from '../model/fitnessplan.model';

describe('FitnessPlannerService', () => {
  let service: FitnessService;
  let httpTestingController: HttpTestingController;

  const mockPlans: FitnessPlan[] = [
    {
      id: 1,
      user: 'John Doe',
      age: 28,
      goal: 'Weight Loss',
      workoutType: 'HIIT',
      durationPerDay: '45 mins',
      dietPlan: 'Low Carb',
      caloriesPerDay: 1800,
      contactNumber: '1234567890'
    },
    {
      id: 2,
      user: 'Emma Smith',
      age: 32,
      goal: 'Muscle Gain',
      workoutType: 'Strength Training',
      durationPerDay: '60 mins',
      dietPlan: 'High Protein',
      caloriesPerDay: 2500,
      contactNumber: '9876543210'
    }
  ];

  beforeEach(() => {
    TestBed.configureTestingModule({
      imports: [HttpClientTestingModule],
      providers: [FitnessService],
    });
    service = TestBed.inject(FitnessService);
    httpTestingController = TestBed.inject(HttpTestingController);
  });

  afterEach(() => {
    httpTestingController.verify();
  });

  fit('should_create_FitnessPlannerService', () => {
    expect(service).toBeTruthy();
  });

  fit('should_retrieve_fitness_plans_via_GET', () => {
    spyOn(service as any, 'getPlans').and.callThrough();
    (service as any).getPlans().subscribe((plans: FitnessPlan[]) => {
      expect(plans).toEqual(mockPlans);
    });

    const req = httpTestingController.expectOne((request) => request.method === 'GET');
    req.flush(mockPlans);

    expect((service as any).getPlans).toHaveBeenCalled();
  });

  fit('should_add_a_fitness_plan_via_POST', () => {
    const newPlan: FitnessPlan = {
      user: 'Alex Johnson',
      age: 25,
      goal: 'Endurance',
      workoutType: 'Cardio',
      durationPerDay: '50 mins',
      dietPlan: 'Balanced Diet',
      caloriesPerDay: 2200,
      contactNumber: '5678901234'
    };

    (service as any).addPlan(newPlan).subscribe((plan: FitnessPlan) => {
      expect(plan).toEqual(newPlan);
    });

    const req = httpTestingController.expectOne(`${(service as any)['backendUrl']}`);
    expect(req.request.method).toBe('POST');
    req.flush(newPlan);
  });

  fit('should_delete_a_fitness_plan_+via_DELETE', () => {
    const planId = 1;

    (service as any).deletePlan(planId).subscribe(() => {});

    const req = httpTestingController.expectOne(`${(service as any)['backendUrl']}/${planId}`);
    expect(req.request.method).toBe('DELETE');
    req.flush({});
  });
});
