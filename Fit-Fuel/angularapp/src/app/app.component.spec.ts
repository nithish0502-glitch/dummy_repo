import { TestBed } from '@angular/core/testing';
import { RouterTestingModule } from '@angular/router/testing';
import { AddFitnessPlanComponent } from './add-fitnessplan/add-fitnessplan.component';
import { ReactiveFormsModule } from '@angular/forms';
import { HttpClientTestingModule } from '@angular/common/http/testing'; 
import { FitnessService } from '../services/fitnessplan.service';

describe('AddFitnessPlanComponent', () => {
  beforeEach(() => TestBed.configureTestingModule({
    imports: [RouterTestingModule, ReactiveFormsModule,  HttpClientTestingModule],
    declarations: [AddFitnessPlanComponent]
  }));

  fit('should_have_as_title_fitness_planning_app', () => {
    const fixture = TestBed.createComponent(AddFitnessPlanComponent);
    const component = fixture.componentInstance;
    expect(component.title).toEqual('Fitness-App');
  });
});
