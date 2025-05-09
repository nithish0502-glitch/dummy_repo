import { Component, OnInit } from '@angular/core';
import { Movie } from '../model/movie.model';
import { MovieService } from '../services/movie.service';

@Component({
  selector: 'app-movie-list',
  templateUrl: './movie-list.component.html',
  styleUrls: ['./movie-list.component.css']
})
export class MovieListComponent implements OnInit {
  movies: Movie[] = [];
  searchQuery: string = ''; // Store the search query
  searchTerm: any;
  filteredMovies: any;

  constructor(private movieService: MovieService) { }

  ngOnInit(): void {
    this.getMovies();
  }

  getMovies(): void {
    this.movieService.getMovies()
      .subscribe(
        (res) => {
          console.log(res);
          this.movies = res;
        },
        (err) => {
          console.error('Error fetching movies:', err);
        }
      );
  }

  deleteMovie(id: any): void {
    this.movieService.deleteMovie(id)
      .subscribe(
        () => {
          // Remove the deleted movie from the list
          this.movies = this.movies.filter(movie => movie.id !== id);
        },
        (err) => {
          console.error('Error deleting movie:', err);
        }
      );
  }

  searchMovie(): void {
    // Filter movies based on search query (case-insensitive search)
    if (this.searchQuery.trim()) {
      this.movies = this.movies.filter(movie =>
        movie.title.toLowerCase().includes(this.searchQuery.toLowerCase()) ||
        movie.director.toLowerCase().includes(this.searchQuery.toLowerCase()) ||
        movie.genre.toLowerCase().includes(this.searchQuery.toLowerCase())
      );
    } else {
      // If search query is empty, fetch all movies
      this.getMovies();
    }
  }
}

