import { ComponentFixture, TestBed } from '@angular/core/testing';
import { CarListComponent } from './car-list.component';
import { CarService } from '../services/car.service';
import { of } from 'rxjs';
import { HttpClientTestingModule } from '@angular/common/http/testing';
import { ReactiveFormsModule } from '@angular/forms';

describe('CarListComponent', () => {
  let component: CarListComponent;
  let fixture: ComponentFixture<CarListComponent>;
  let service: CarService;

  const mockCars = [
    {
      id: 1,
      make: 'Toyota',
      model: 'Corolla',
      year: 2020,
      category: 'Sedan',
      rating: 8,
    },
    {
      id: 2,
      make: 'Honda',
      model: 'Civic',
      year: 2019,
      category: 'Sedan',
      rating: 9,
    },
  ];

  beforeEach(() => {
    TestBed.configureTestingModule({
      declarations: [CarListComponent],
      providers: [CarService],
      imports: [HttpClientTestingModule, ReactiveFormsModule],
    });
    fixture = TestBed.createComponent(CarListComponent);
    component = fixture.componentInstance;
    service = TestBed.inject(CarService);
    fixture.detectChanges();
  });

  fit('should_create_CarListComponent', () => {
    expect(component).toBeTruthy();
  });

  fit('should_call_getCars', () => {
    spyOn((service as any), 'getCars').and.returnValue(of(mockCars));
    (component as any).ngOnInit();
    expect((service as any).getCars).toHaveBeenCalled();
    expect((component as any).cars).toEqual(mockCars);
  });

  fit('should_call_deleteCar', () => {
    const carId = 1;
    spyOn((service as any), 'deleteCar').and.returnValue(of());
    (component as any).deleteCar(carId);
    expect((service as any).deleteCar).toHaveBeenCalledWith(carId);
  });

  fit('should_filter_cars_when_search_term_is_entered', () => {
    spyOn((service as any), 'searchCars').and.returnValue(of([mockCars[0]]));
    (component as any).searchTerm = 'Toyota';
    (component as any).searchCars();
    expect((service as any).searchCars).toHaveBeenCalledWith('Toyota');
    expect((component as any).cars).toEqual([mockCars[0]]);
  });
});
