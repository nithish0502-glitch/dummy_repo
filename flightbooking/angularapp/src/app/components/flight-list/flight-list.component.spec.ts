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

  fit('Frontend_day27_should have updateFlight method', () => {
    expect((component as any).updateFlight).toBeDefined();
    expect(typeof (component as any).updateFlight).toBe('function');
  });

  fit('Frontend_day27_should have deleteFlight method', () => {
    expect((component as any).deleteFlight).toBeDefined();
    expect(typeof (component as any).deleteFlight).toBe('function');
  });


});
