import { ComponentFixture, TestBed } from '@angular/core/testing';

import { ManageBookingComponent } from './manage-booking.component';
import { RouterTestingModule } from '@angular/router/testing';
import { HttpClientTestingModule } from '@angular/common/http/testing';

describe('ManageBookingComponent', () => {
  let component: ManageBookingComponent;
  let fixture: ComponentFixture<ManageBookingComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      imports: [HttpClientTestingModule, 
        RouterTestingModule 
      ], 
      declarations: [ ManageBookingComponent ]
    })
    .compileComponents();
  });

  beforeEach(() => {
    fixture = TestBed.createComponent(ManageBookingComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  fit('Frontend_day26_should have bookings arrays declared', () => {
    expect(component).toBeTruthy();
    expect((component as any).bookings).toBeDefined();
  });

});
