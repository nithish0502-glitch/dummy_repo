import { TestBed } from '@angular/core/testing';

import { FitnessplanService } from './fitnessplan.service';

describe('FitnessplanService', () => {
  let service: FitnessplanService;

  beforeEach(() => {
    TestBed.configureTestingModule({});
    service = TestBed.inject(FitnessplanService);
  });

  it('should be created', () => {
    expect(service).toBeTruthy();
  });
});
