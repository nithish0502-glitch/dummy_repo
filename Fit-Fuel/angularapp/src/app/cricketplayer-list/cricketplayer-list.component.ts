import { Component, OnInit } from '@angular/core';
import { CricketPlayer } from '../model/cricketplayer.model';
import { CricketPlayerService } from '../services/cricketplayer.service';

@Component({
  selector: 'app-cricket-player-list',
  templateUrl: './cricketplayer-list.component.html',
  styleUrls: ['./cricketplayer-list.component.css']
})
export class CricketPlayerListComponent implements OnInit {
  players: CricketPlayer[] = [];
  isAscending: boolean = true;

  constructor(private cricketPlayerService: CricketPlayerService) { }

  ngOnInit(): void {
    this.getPlayers();
  }

  getPlayers(): void {
    this.cricketPlayerService.getPlayers()
      .subscribe(
        (res) => {
          console.log(res);
          this.players = res;
        },
        (err) => {
          console.error('Error fetching players:', err);
        }
      );
  }

  deletePlayer(id: number): void {
    this.cricketPlayerService.deletePlayer(id)
      .subscribe(
        () => {
          this.players = this.players.filter(player => player.id !== id);
        },
        (err) => {
          console.error('Error deleting player:', err);
        }
      );
  }

  sortByRuns(): void {
    if (this.isAscending) {
      this.players.sort((a, b) => (a.totalRuns ?? 0) - (b.totalRuns ?? 0));
    } else {
      this.players.sort((a, b) => (b.totalRuns ?? 0) - (a.totalRuns ?? 0));
    }
    this.isAscending = !this.isAscending;
  }
}

