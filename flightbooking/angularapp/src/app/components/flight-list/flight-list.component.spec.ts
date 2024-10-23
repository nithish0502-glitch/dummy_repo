import { ComponentFixture, TestBed } from '@angular/core/testing';

import { FlightListComponent } from './flight-list.component';
import { HttpClientTestingModule } from '@angular/common/http/testing';
import { RouterTestingModule } from '@angular/router/testing';

describe('FlightListComponent', () => {
  let component: FlightListComponent;
  let fixture: ComponentFixture<FlightListComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      imports: [HttpClientTestingModule, 
        RouterTestingModule 
      ], 
      declarations: [ FlightListComponent ]
    })
    .compileComponents();
  });

  beforeEach(() => {
    fixture = TestBed.createComponent(FlightListComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  fit('Frontend_day26_create_Flight_List_Component with flights array', () => {
    expect(component).toBeTruthy();
    expect((component as any).flights).toBeDefined();
  });

  fit('should have updateFlight method', () => {
    expect(component.updateFlight).toBeDefined();
    expect(typeof component.updateFlight).toBe('function');
  });

  fit('should have deleteFlight method', () => {
    expect(component.deleteFlight).toBeDefined();
    expect(typeof component.deleteFlight).toBe('function');
  });


});
