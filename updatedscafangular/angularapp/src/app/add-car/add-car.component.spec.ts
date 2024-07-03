import { ComponentFixture, TestBed, fakeAsync, tick } from '@angular/core/testing';
import { FormBuilder, ReactiveFormsModule, Validators } from '@angular/forms';
import { of } from 'rxjs';
import { HttpClientTestingModule } from '@angular/common/http/testing';
import { DebugElement } from '@angular/core';
import { AddCarComponent } from './add-car.component';
import { CarService } from '../services/car.service';
import { RouterTestingModule } from '@angular/router/testing';
import { Router } from '@angular/router';

describe('AddCarComponent', () => {
  let component: AddCarComponent;
  let fixture: ComponentFixture<AddCarComponent>;
  let service: CarService;
  let debugElement: DebugElement;
  let formBuilder: FormBuilder;
  let router: Router;

  beforeEach(() => {
    TestBed.configureTestingModule({
      declarations: [AddCarComponent],
      imports: [ReactiveFormsModule, HttpClientTestingModule, RouterTestingModule],
      providers: [CarService],
    });
    formBuilder = TestBed.inject(FormBuilder);
    fixture = TestBed.createComponent(AddCarComponent);
    component = fixture.componentInstance;
    service = TestBed.inject(CarService);
    fixture.detectChanges();
    router = TestBed.inject(Router);
    spyOn(router, 'navigateByUrl').and.returnValue(Promise.resolve(true));
  });

  fit('should_create_AddCarComponent', () => {
    expect(component).toBeTruthy();
  });

  fit('should_add_a_new_car_when_form_is_valid', fakeAsync(() => {
    const mockCar = {
      make: 'Toyota',
      model: 'Corolla',
      year: 2020,
      category: 'Sedan',
      rating: 8,
    };

    spyOn((service as any), 'addCar').and.returnValue(of(mockCar));

    (component as any).carForm.setValue(mockCar);
    let value: boolean = (component as any).carForm.valid;
    (component as any).addNewCar();
    tick();

    expect(value).toBeTruthy();
    expect((service as any).addCar).toHaveBeenCalledWith(mockCar);
    expect(router.navigateByUrl).toHaveBeenCalledWith('/cars'); // Verify navigation
  }));

  fit('should_add_all_the_required_fields', () => {
    const form = (component as any).carForm;
    form.setValue({
      make: '',
      model: '',
      year: '',
      category: '',
      rating: '',
    });

    expect(form.valid).toBeFalsy();
    expect(form.get('make')?.hasError('required')).toBeTruthy();
    expect(form.get('model')?.hasError('required')).toBeTruthy();
    expect(form.get('year')?.hasError('required')).toBeTruthy();
    expect(form.get('category')?.hasError('required')).toBeTruthy();
    expect(form.get('rating')?.hasError('required')).toBeTruthy();
  });

  fit('should_validate_make_as_valid_when_more_than_2_characters', () => {
    const form = (component as any).carForm;
    const makeControl = form.controls['make'];
    makeControl.setValue('Toyota');
    expect(makeControl.valid).toBeTruthy();
  });
});
