import { ComponentFixture, TestBed } from '@angular/core/testing';
import { ReactiveFormsModule, FormBuilder, Validators } from '@angular/forms';
import { AddMovieComponent } from './add-movie.component';
import { MovieService } from '../services/movie.service';
import { of } from 'rxjs';
import { Router } from '@angular/router';
import { DebugElement } from '@angular/core';
import { HttpClientTestingModule } from '@angular/common/http/testing';
import { RouterTestingModule } from '@angular/router/testing';

describe('AddMovieComponent', () => {
  let component: AddMovieComponent;
  let fixture: ComponentFixture<AddMovieComponent>;
  let service: MovieService;
  let debugElement: DebugElement;
  let formBuilder: FormBuilder;
  let router: Router;

  beforeEach(() => {
    TestBed.configureTestingModule({
      declarations: [AddMovieComponent],
      imports: [ReactiveFormsModule, HttpClientTestingModule, RouterTestingModule],
      providers: [MovieService],
    });

    formBuilder = TestBed.inject(FormBuilder);
    fixture = TestBed.createComponent(AddMovieComponent);
    component = fixture.componentInstance;
    service = TestBed.inject(MovieService);
    fixture.detectChanges();
    router = TestBed.inject(Router);
    spyOn(router, 'navigateByUrl').and.returnValue(Promise.resolve(true));
  });

  fit('should create AddMovieComponent', () => {
    expect(component).toBeTruthy();
  });

  fit('should add a new movie when form is valid', () => {
    const mockMovie = {
      title: 'Avatar',
      director: 'James Cameron',
      genre: 'Sci-Fi',
      releaseYear: 2009,
      rating: 7.8,
      duration: 162,
    };

    spyOn((service as any), 'addMovie').and.returnValue(of(mockMovie)); // Mock the addMovie method

    (component as any).movieForm.setValue(mockMovie); // Set form values
    (component as any).addNewMovie(); // Trigger the addNewMovie method
    
    expect((component as any).movieForm.valid).toBeTruthy();
    expect((service as any).addMovie).toHaveBeenCalledWith(mockMovie);
    expect(router.navigateByUrl).toHaveBeenCalledWith('/movies');
  });

  fit('should require all form fields to be filled in', () => {
    const form = component.movieForm;

    form.setValue({
      title: '',
      director: '',
      genre: '',
      releaseYear: '',
      rating: '',
      duration: '',
    });

    expect(form.valid).toBeFalsy();
    expect(form.get('title')?.hasError('required')).toBeTruthy();
    expect(form.get('director')?.hasError('required')).toBeTruthy();
    expect(form.get('genre')?.hasError('required')).toBeTruthy();
    expect(form.get('releaseYear')?.hasError('required')).toBeTruthy();
    expect(form.get('rating')?.hasError('required')).toBeTruthy();
    expect(form.get('duration')?.hasError('required')).toBeTruthy();
  });

  fit('should validate movie rating and release year', () => {
    const form = (component as any).movieForm;

    form.setValue({
      title: 'Avatar',
      director: 'James Cameron',
      genre: 'Sci-Fi',
      releaseYear: 2009, // Valid year
      rating: 7.8, // Valid rating
      duration: 162,
    });

    expect(form.valid).toBeTruthy();
    expect(form.get('rating')?.hasError('min')).toBeFalsy();
    expect(form.get('rating')?.hasError('max')).toBeFalsy();

    form.setValue({
      title: 'Avatar',
      director: 'James Cameron',
      genre: 'Sci-Fi',
      releaseYear: 1899, // Invalid year
      rating: 11, // Invalid rating
      duration: 162,
    });

    expect(form.invalid).toBeTruthy();
    expect(form.get('releaseYear')?.hasError('min')).toBeTruthy();
    expect(form.get('rating')?.hasError('max')).toBeTruthy();
  });
});
