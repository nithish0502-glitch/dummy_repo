import { TestBed } from '@angular/core/testing';
import { HttpClientTestingModule, HttpTestingController } from '@angular/common/http/testing';
import { MovieService } from './movie.service';

describe('MovieService', () => {
  let service: MovieService;
  let httpTestingController: HttpTestingController;

  const mockMovies = [
    {
      id: 1,
      title: 'Fast and Furious',
      director: 'Rob Cohen',
      genre: 'Action',
      releaseYear: 2001,
      rating: 7.1,
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
      imports: [HttpClientTestingModule],
      providers: [MovieService],
    });
    service = TestBed.inject(MovieService);
    httpTestingController = TestBed.inject(HttpTestingController);
  });

  afterEach(() => {
    // Ensure that there are no outstanding requests after each test
    httpTestingController.verify();
  });

  fit('should create MovieService', () => {
    expect(service).toBeTruthy();
  });

  fit('should retrieve movies from the API via GET', () => {
    spyOn((service as any), 'getMovies').and.callThrough();
    (service as any).getMovies().subscribe((movies: any) => {
      expect(movies).toEqual(mockMovies);
    });

    const req = httpTestingController.expectOne((request) => request.method === 'GET');
    req.flush(mockMovies);

    expect((service as any).getMovies).toHaveBeenCalled();
  });

  fit('should add a movie via POST', () => {
    const newMovie = {
      title: 'Deadpool',
      director: 'Tim Miller',
      genre: 'Action',
      releaseYear: 2016,
      rating: 8.0,
      duration: 108,
    };

    (service as any).addMovie(newMovie).subscribe((movie: any) => {
      expect(movie).toEqual(newMovie);
    });

    const req = httpTestingController.expectOne(`${service['backendUrl']}`);
    expect(req.request.method).toEqual('POST');
    req.flush(newMovie);
  });

  fit('should delete a movie via DELETE', () => {
    const movieId = 1;

    (service as any).deleteMovie(movieId).subscribe(() => {});

    const req = httpTestingController.expectOne(`${(service as any)['backendUrl']}/${movieId}`);
    expect(req.request.method).toEqual('DELETE');
    req.flush({});
  });
});
