import { TestBed } from '@angular/core/testing';
import { HttpClientTestingModule, HttpTestingController } from '@angular/common/http/testing';
import { CarService } from './car.service';

describe('CarService', () => {
  let service: CarService;
  let httpTestingController: HttpTestingController;

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
      imports: [HttpClientTestingModule],
      providers: [CarService],
    });
    service = TestBed.inject(CarService);
    httpTestingController = TestBed.inject(HttpTestingController);
  });

  afterEach(() => {
    // Ensure that there are no outstanding requests after each test
    httpTestingController.verify();
  });

  fit('should_be_created', () => {
    expect(service).toBeTruthy();
  });

  fit('should_retrieve_cars_from_the_API_via_GET', () => {
    (service as any).getCars().subscribe((cars) => {
      expect(cars).toEqual(mockCars);
    });

    const req = httpTestingController.expectOne(`${service['backendUrl']}`);
    expect(req.request.method).toEqual('GET');
    req.flush(mockCars);
  });

  fit('should_add_a_car_via_POST', () => {
    const newCar = {
      make: 'Ford',
      model: 'Mustang',
      year: 2021,
      category: 'Coupe',
      rating: 9.5,
    };

    (service as any).addCar(newCar).subscribe((car) => {
      expect(car).toEqual(newCar);
    });
    const req = httpTestingController.expectOne(`${service['backendUrl']}`);
    expect(req.request.method).toEqual('POST');
    req.flush(newCar);
  });

  fit('should_delete_a_car_via_DELETE', () => {
    const carId = 1;

    (service as any).deleteCar(carId).subscribe(() => {});

    const req = httpTestingController.expectOne(`${service['backendUrl']}/${carId}`);
    expect(req.request.method).toEqual('DELETE');
    req.flush({});
  });

  fit('should_search_cars_via_GET', () => {
    const searchTerm = 'Toyota';
    (service as any).searchCars(searchTerm).subscribe((cars) => {
      expect(cars).toEqual([mockCars[0]]);
    });

    const req = httpTestingController.expectOne(`${service['backendUrl']}?q=${searchTerm}`);
    expect(req.request.method).toEqual('GET');
    req.flush([mockCars[0]]);
  });
});
