import { TestBed } from '@angular/core/testing';
import { HttpClientTestingModule, HttpTestingController } from '@angular/common/http/testing';
import { CricketPlayerService } from './cricketplayer.service';
import { CricketPlayer } from '../model/cricketplayer.model';

describe('CricketPlayerService', () => {
  let service: CricketPlayerService;
  let httpTestingController: HttpTestingController;

  const mockPlayers: CricketPlayer[] = [
    {
      id: 1,
      name: 'Virat Kohli',
      age: 34,  
      team: 'India',
      position: 'Batsman',
      battingStyle: 'Right-handed',
      bowlingStyle: 'Right-arm medium',
      totalRuns: 12000,
      totalWickets: 150,
      totalMatches: 275  
    },
    {
      id: 2,
      name: 'Jasprit Bumrah',
      age: 29,  
      team: 'India',
      position: 'Bowler',
      battingStyle: 'Right-handed',
      bowlingStyle: 'Right-arm fast',
      totalRuns: 200,
      totalWickets: 250,
      totalMatches: 85  
    },
    {
      id: 3,
      name: 'Rishabh Pant',
      age: 25,  
      team: 'India',
      position: 'Wicketkeeper',
      battingStyle: 'Left-handed',
      bowlingStyle: 'N/A',
      totalRuns: 2500,
      totalWickets: 0,
      totalMatches: 60  
    },
  ];

  beforeEach(() => {
    TestBed.configureTestingModule({
      imports: [HttpClientTestingModule],
      providers: [CricketPlayerService],
    });
    service = TestBed.inject(CricketPlayerService);
    httpTestingController = TestBed.inject(HttpTestingController);
  });

  afterEach(() => {
    // Ensure that there are no outstanding requests after each test
    httpTestingController.verify();
  });

  it('should create CricketPlayerService', () => {
    expect(service).toBeTruthy();
  });

  it('should retrieve players from the API via GET', () => {
    spyOn(service, 'getPlayers').and.callThrough();
    service.getPlayers().subscribe((players: CricketPlayer[]) => {
      expect(players).toEqual(mockPlayers);
    });

    const req = httpTestingController.expectOne(service['backendUrl']);
    req.flush(mockPlayers);

    expect(service.getPlayers).toHaveBeenCalled();
  });

  it('should add a player via POST', () => {
    const newPlayer: CricketPlayer = {
      name: 'Shubman Gill',
      age: 23,
      team: 'India',
      position: 'Batsman',
      battingStyle: 'Right-handed',
      bowlingStyle: 'N/A',
      totalRuns: 1200,
      totalWickets: 0,
      totalMatches: 25
    };

    service.addPlayer(newPlayer).subscribe((player: CricketPlayer) => {
      expect(player).toEqual(newPlayer);
    });

    const req = httpTestingController.expectOne(`${service['backendUrl']}`);
    expect(req.request.method).toEqual('POST');
    req.flush(newPlayer);
  });

  it('should delete a player via DELETE', () => {
    const playerId = 1;

    service.deletePlayer(playerId).subscribe(() => {});

    const req = httpTestingController.expectOne(`${service['backendUrl']}/${playerId}`);
    expect(req.request.method).toEqual('DELETE');
    req.flush({});
  });
});
