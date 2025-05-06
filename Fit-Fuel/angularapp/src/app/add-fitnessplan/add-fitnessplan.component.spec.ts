import { ComponentFixture, TestBed } from '@angular/core/testing';

import { AddFitnessplanComponent } from './add-fitnessplan.component';

describe('AddFitnessplanComponent', () => {
  let component: AddFitnessplanComponent;
  let fixture: ComponentFixture<AddFitnessplanComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ AddFitnessplanComponent ]
    })
    .compileComponents();
  });

  beforeEach(() => {
    fixture = TestBed.createComponent(AddFitnessplanComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
