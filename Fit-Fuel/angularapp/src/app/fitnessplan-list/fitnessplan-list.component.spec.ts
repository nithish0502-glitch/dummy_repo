import { ComponentFixture, TestBed } from '@angular/core/testing';

import { FitnessplanListComponent } from './fitnessplan-list.component';

describe('FitnessplanListComponent', () => {
  let component: FitnessplanListComponent;
  let fixture: ComponentFixture<FitnessplanListComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ FitnessplanListComponent ]
    })
    .compileComponents();
  });

  beforeEach(() => {
    fixture = TestBed.createComponent(FitnessplanListComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
