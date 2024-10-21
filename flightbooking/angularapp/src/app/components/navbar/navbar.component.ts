import { Component, OnInit } from '@angular/core';
import { Router } from '@angular/router';
import { AuthService } from 'src/app/service/auth.service'; 

@Component({
  selector: 'app-navbar',
  templateUrl: './navbar.component.html',
  styleUrls: ['./navbar.component.css']
})
export class NavbarComponent implements OnInit {
  showDropdown = false;
  showLogoutPopup = false;
  isLoggedIn: boolean = false;
  userRole: string | null = '';
  userName: string | null = '';

  constructor(private authService: AuthService, private router: Router) {
    // Subscribe to authentication changes
    this.authService.isAuthenticated$.subscribe((authenticated: boolean) => {
      this.isLoggedIn = authenticated;
      this.userRole = localStorage.getItem('userRole');
      this.userName = localStorage.getItem('username');
    });
  }

  ngOnInit(): void {
    // Initial state based on authentication
    this.isLoggedIn = this.authService.isAuthenticated();
    this.userRole = localStorage.getItem('userRole');
    this.userName = localStorage.getItem('username');
  }

  logout(): void {
    this.authService.logout(); // Log the user out
    // Clear user role and name from localStorage
    localStorage.removeItem('userRole');
    localStorage.removeItem('username');
    
    this.isLoggedIn = false; // Update local state immediately
    this.userRole = null; // Clear user role
    this.userName = null; // Clear user name

    this.router.navigate(['/login']); // Redirect to login page
  }
}
