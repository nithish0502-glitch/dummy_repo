import { TestBed } from '@angular/core/testing';

import { CricketplayerService } from './cricketplayer.service';

describe('CricketplayerService', () => {
  let service: CricketplayerService;

  beforeEach(() => {
    TestBed.configureTestingModule({});
    service = TestBed.inject(CricketplayerService);
  });

  it('should be created', () => {
    expect(service).toBeTruthy();
  });
});
