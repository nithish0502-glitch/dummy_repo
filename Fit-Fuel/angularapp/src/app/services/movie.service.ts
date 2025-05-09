import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Observable } from 'rxjs';
import { Movie } from '../model/movie.model';

@Injectable({
  providedIn: 'root'
})
export class MovieService {
  private backendUrl = 'https://your-backend-url.com/api/movies'; // Replace with your backend URL

  constructor(private http: HttpClient) { }

  getMovies(): Observable<Movie[]> {
    return this.http.get<Movie[]>(this.backendUrl);
  }

  addMovie(movie: Movie): Observable<Movie> {
    return this.http.post<Movie>(this.backendUrl, movie);
  }

  deleteMovie(id: number): Observable<void> {
    const url = `${this.backendUrl}/${id}`;
    return this.http.delete<void>(url);
  }
}

