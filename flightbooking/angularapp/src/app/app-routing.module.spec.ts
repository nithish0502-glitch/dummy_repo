import { TestBed } from '@angular/core/testing';
import { RouterTestingModule } from '@angular/router/testing';
import { AppRoutingModule } from './app-routing.module';
import { Router } from '@angular/router';
import { HttpClientModule } from '@angular/common/http';
import { HttpClientTestingModule } from '@angular/common/http/testing';

describe('AppRoutingModule', () => {
  let router: Router;

  beforeEach(() => {
    TestBed.configureTestingModule({
      imports: [
        AppRoutingModule,
        RouterTestingModule.withRoutes([]),
        HttpClientModule, HttpClientTestingModule
      ]
    });

    router = TestBed.inject(Router);
    router.initialNavigation(); // Initialize the router
  });

  fit('Frontend_day29_should navigate to "add-flight" to load AddFlightComponent', async () => {
    await router.navigate(['add-flight']);
    expect(router.url).toBe('/add-flight');
  });

  fit('Frontend_day29_should navigate to "book-form" to load BookingFormComponent', async () => {
    await router.navigate(['book-form']);
    expect(router.url).toBe('/book-form');
  });


});
