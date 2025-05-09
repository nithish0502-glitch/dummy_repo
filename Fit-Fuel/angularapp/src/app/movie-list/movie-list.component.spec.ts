import { ComponentFixture, TestBed } from '@angular/core/testing';
import { MovieListComponent } from './movie-list.component';
import { MovieService } from '../services/movie.service';
import { of } from 'rxjs';
import { HttpClientTestingModule } from '@angular/common/http/testing';

describe('MovieListComponent', () => {
  let component: MovieListComponent;
  let fixture: ComponentFixture<MovieListComponent>;
  let service: MovieService;

  const mockMovies = [
    {
      id: 1,
      title: 'Fast and Furious',
      director: 'Rob Cohen',
      genre: 'Action',
      releaseYear: 2001,
      rating: 6.8,
      duration: 106,
    },
    {
      id: 2,
      title: 'Avatar',
      director: 'James Cameron',
      genre: 'Sci-Fi',
      releaseYear: 2009,
      rating: 7.8,
      duration: 162,
    },
  ];

  beforeEach(() => {
    TestBed.configureTestingModule({
      declarations: [MovieListComponent],
      providers: [MovieService],
      imports: [HttpClientTestingModule],
    });

    fixture = TestBed.createComponent(MovieListComponent);
    component = fixture.componentInstance;
    service = TestBed.inject(MovieService);
  });

  fit('should create MovieListComponent', () => {
    expect(component).toBeTruthy();
  });

  fit('should call getMovies and fetch movies list', () => {
    spyOn((service as any), 'getMovies').and.returnValue(of(mockMovies));
    (component as any).getMovies();
    expect((component as any).getMovies).toBeDefined();
    expect((component as any).getMovies instanceof Function).toBeTruthy();
    expect((service as any).getMovies).toHaveBeenCalled();
    expect(component.movies.length).toBe(2); // Check if the correct number of movies are fetched
  });

  fit('should call deleteMovie and remove movie from list', () => {
    spyOn((service as any), 'deleteMovie').and.returnValue(of());
    (component as any).deleteMovie(1); // Delete movie with ID 1
    expect((component as any).deleteMovie).toBeDefined();
    expect((component as any).deleteMovie instanceof Function).toBeTruthy();
    expect((service as any).deleteMovie).toHaveBeenCalledWith(1);
    expect(component.movies.length).toBe(1); // Verify if the movie was removed
  });
});
