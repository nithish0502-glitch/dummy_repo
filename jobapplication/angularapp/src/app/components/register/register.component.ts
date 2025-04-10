import { Component, OnInit } from '@angular/core';
import { AuthService } from 'src/app/services/auth.service'; 
import { FormBuilder, FormGroup, Validators } from '@angular/forms';
import { Router } from '@angular/router';
import { User } from 'src/app/models/user.model';
import { ChangeDetectorRef } from '@angular/core';
 
@Component({
  selector: 'app-registration',
  templateUrl: './register.component.html',
  styleUrls: ['./register.component.css']
})
export class RegisterComponent implements OnInit {

  registrationForm!: FormGroup;
  username: string = "";
  password: string = "";
  errorMessage: string;
  confirmPassword: string = "";
  mobileNumber: string = "";
  userRole: string = "";
  email: string = "";
  passwordMismatch: boolean = false;
  isRegisteredSuccessfully: boolean = false;
 
  constructor(
    private authService: AuthService, 
    private router: Router, 
    private fb: FormBuilder,
    private cd: ChangeDetectorRef
  ) { }

  ngOnInit() {
    this.registrationForm = this.fb.group({
      mobileNumber: ['', [Validators.required, Validators.pattern("^((\\+91-?)|0)?[0-9]{10}$")]],
      email: ['', [Validators.required, Validators.email]],
    });
  }

  register(): void {
    if (this.password !== this.confirmPassword) {
      this.passwordMismatch = true;
      return;
    }

    this.passwordMismatch = false;

    if (!this.isPasswordComplex(this.password)) {
      return; // Password complexity check failed
    }

    const user: User = {
      username: this.username,
      password: this.password,
      userRole: this.userRole,
      email: this.email,
      mobileNumber: this.mobileNumber
    };

    this.authService.register(user).subscribe(
      (response: any) => {
        console.log("Response................." + response.status);
        if (response && response.status === 201) {
          console.log('Registration successful, showing popup...');
          this.isRegisteredSuccessfully = true; // Show success popup
          this.cd.detectChanges(); // Trigger change detection
        } else if (response && response.error && response.error.Message) {
          this.errorMessage = response.error.Message;
        }
      },
      (error: any) => {
        console.log(error);
        if (error.status === 400) {
          this.errorMessage = "Bad request. Please check your input.";
        } else if (error.status === 409) {
          this.errorMessage = "User already exists.";
        } else {
          this.errorMessage = "Registration failed. Please try again.";
        }
      }
    );
  }

  isPasswordComplex(password: string): boolean {
    const hasUppercase = /[A-Z]/.test(password);
    const hasLowercase = /[a-z]/.test(password);
    const hasDigit = /\d/.test(password);
    const hasSpecialChar = /[!@#$%^&*()_+{}\[\]:;<>,.?~\-]/.test(password);

    return hasUppercase && hasLowercase && hasDigit && hasSpecialChar;
  }

  handleOk() {
    this.isRegisteredSuccessfully = false; // Close the popup
    this.router.navigate(['/login']); // Redirect to login page
  }

  handleKeydown(event: KeyboardEvent) {
    if (event.key === 'Enter' || event.key === 'Space') {
      this.handleOk(); // Call handleOk when Enter or Space is pressed
    }
  }
}
