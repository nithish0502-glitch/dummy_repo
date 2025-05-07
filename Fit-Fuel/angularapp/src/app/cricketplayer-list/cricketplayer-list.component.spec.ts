import { ComponentFixture, TestBed } from '@angular/core/testing';
import { CricketPlayerListComponent } from './cricketplayer-list.component';
import { CricketPlayerService } from '../services/cricketplayer.service';
import { of } from 'rxjs';
import { HttpClientTestingModule } from '@angular/common/http/testing';

describe('CricketPlayerListComponent', () => {
  let component: CricketPlayerListComponent;
  let fixture: ComponentFixture<CricketPlayerListComponent>;
  let service: CricketPlayerService;

  const mockPlayers = [
    {
      id: 1,
      name: 'Virat Kohli',
      age: 35,
      team: 'India',
      position: 'Batsman',
      battingStyle: 'Right-hand bat',
      bowlingStyle: 'Right-arm medium',
      totalRuns: 12000,
      totalWickets: 5,
      totalMatches: 250
    },
    {
      id: 2,
      name: 'Jasprit Bumrah',
      age: 31,
      team: 'India',
      position: 'Bowler',
      battingStyle: 'Right-hand bat',
      bowlingStyle: 'Right-arm fast',
      totalRuns: 200,
      totalWickets: 200,
      totalMatches: 100
    }
  ];

  beforeEach(() => {
    TestBed.configureTestingModule({
      declarations: [CricketPlayerListComponent],
      providers: [CricketPlayerService],
      imports: [HttpClientTestingModule],
    });

    fixture = TestBed.createComponent(CricketPlayerListComponent);
    component = fixture.componentInstance;
    service = TestBed.inject(CricketPlayerService);
  });

  fit('should create CricketPlayerListComponent', () => {
    expect(component).toBeTruthy();
  });

  fit('should call getPlayers', () => {
    spyOn((service as any), 'getPlayers').and.returnValue(of(mockPlayers));
    (component as any).getPlayers();
    expect((component as any).getPlayers).toBeDefined();
    expect((component as any).getPlayers instanceof Function).toBeTruthy();
    expect((service as any).getPlayers).toHaveBeenCalled();
  });

  fit('should call deletePlayer', () => {
    spyOn((service as any), 'deletePlayer').and.returnValue(of());
    (component as any).players = [...mockPlayers];
    (component as any).deletePlayer(1);
    expect((component as any).deletePlayer).toBeDefined();
    expect((component as any).deletePlayer instanceof Function).toBeTruthy();
    expect((service as any).deletePlayer).toHaveBeenCalledWith(1);
  });
});
