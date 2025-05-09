import { Injectable } from '@angular/core';
import { HttpClient, HttpResponse } from '@angular/common/http';
import { BehaviorSubject, Observable, of } from 'rxjs';
import { catchError, tap } from 'rxjs/operators';
import { apiUrl } from 'src/apiconfig'; 
import { User } from '../models/user.model';
import { Login } from '../models/login.model';
  
@Injectable({
  providedIn: 'root'
})
export class AuthService {
  private currentUserSubject: BehaviorSubject<string | null>;
  public currentUser: Observable<string | null>;
  public apiUrl = apiUrl; // Replace with your Spring Boot backend URL
  private userRoleSubject = new BehaviorSubject<string>('');
  private userIdSubject = new BehaviorSubject<string>('');
  userRole$: Observable<string> = this.userRoleSubject.asObservable();
  userId$: Observable<string> = this.userIdSubject.asObservable();
  private isAuthenticatedSubject: BehaviorSubject<boolean> = new BehaviorSubject<boolean>(this.isAuthenticated());
  isAuthenticated$ = this.isAuthenticatedSubject.asObservable();

  constructor(private http: HttpClient) {
    this.currentUserSubject = new BehaviorSubject<string | null>(
      localStorage.getItem('currentUser')
    );
    this.currentUser = this.currentUserSubject.asObservable();
  }

  register(user: User): Observable<HttpResponse<any>> {
    const body = user;
    console.log("inservice", body);

    return this.http.post<HttpResponse<any>>(`${this.apiUrl}/api/register`, body, { observe: 'response' }).pipe(
      tap((response: HttpResponse<any>) => {
        // Assuming 'storeUserData' is for storing session or other data based on the response body
        this.storeUserData(response.body);
      }),
      catchError(this.handleError<any>('register', true)) // Handle errors properly
    );
  }

  login(login: Login): Observable<any> {
    const loginData = login;
    console.log(loginData);
    return this.http.post<Login>(`${this.apiUrl}/api/login`, loginData) // Corrected line
      .pipe(
        tap(response => {
          console.log(response.token);
          localStorage.setItem('token', response.token);
          const decodedToken = this.decodeToken(response.token);

          localStorage.setItem('token', response.token);
          localStorage.setItem('userRole', response.userRole);
          localStorage.setItem('username', response.username);
          localStorage.setItem('userId', response.userId);
          console.log(localStorage.getItem('userId'));
          console.log(localStorage.getItem('username'));

          if (decodedToken) {
            console.log(localStorage.getItem('userRole'));
            // Update BehaviorSubjects
            this.userRoleSubject.next(decodedToken['role']);
            this.userIdSubject.next(decodedToken['nameid']);
            this.isAuthenticatedSubject.next(true);
          } else {
            console.error('Unable to decode token or missing claims');
          }
        })
      );
  }

  logout(): void {
    // Remove the token, role, and user ID from storage upon logout
    localStorage.clear();
    this.currentUserSubject.next(null);
  }

  isAuthenticated(): boolean {
    // Check if the user is authenticated by verifying the token
    const token = localStorage.getItem('token');
    console.log(token);

    return !!token; // Return true if the token exists
  }

  isAdmin(): boolean {
    const token = localStorage.getItem('userRole');
    const userId= localStorage.getItem('userId');
    const username =localStorage.getItem('username');
     console.log(localStorage.getItem('userId'));
     console.log(localStorage.getItem('userRole'));
     console.log(localStorage.getItem('username'))


  if (token=="Admin") {
    //const decodedToken = this.decodeToken(token);
    console.log(token);
    return true;
  }
  return false;
}


  isCustomer(): boolean {
  
    const token = localStorage.getItem('userRole');
    const userId= localStorage.getItem('userId');
    const username =localStorage.getItem('username');
     console.log(localStorage.getItem('userId'));
     console.log(localStorage.getItem('userRole'));
     console.log(localStorage.getItem('username'));
     if (token=="User") {
      //const decodedToken = this.decodeToken(token);
      console.log(token);
      return true;
    }
    return false;
  }

  // getCustomerName(): string {
  //   const token = localStorage.getItem('username');
  //   if (token) {
  //     const decodedToken = this.decodeToken(token);
  //     return decodedToken['http://schemas.xmlsoap.org/ws/2005/05/identity/claims/name'];
  //   }
  //   return '';
  // }


  getCustomerName(): string {
    const token = localStorage.getItem('username');
    console.log(token);
    if (token) {
     // const decodedToken = this.decodeToken(token);
     // return decodedToken['http://schemas.xmlsoap.org/ws/2005/05/identity/claims/name'];
     return token;
    }
    return '';
  }

  private storeUserData(user: any): void {
    localStorage.setItem('token', user.token);
    localStorage.setItem('userRole', user.role);
    localStorage.setItem('userId', user.userId);
    localStorage.setItem('username', user.username);

  }

  private decodeToken(token: string): any {
    try {
      const decoded = JSON.parse(atob(token.split('.')[1]));
      console.log(decoded);
      return decoded;
    } catch (error) {
      return null;
    }
  }



  private handleError<T>(operation = 'operation', returnError = false , result?: T) {
    return (error: any): Observable<T> => {
      console.error(error); // log to console instead

      if (returnError) {
        // Let the app keep running by returning an observable with the error message
        return of(error as T);
      }

      // Let the app keep running by returning an empty result.
      return of(result as T);
    };
  }

}