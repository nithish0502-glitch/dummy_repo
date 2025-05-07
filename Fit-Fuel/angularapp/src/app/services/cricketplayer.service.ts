import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Observable } from 'rxjs';
import { CricketPlayer } from '../model/cricketplayer.model';  // Update the path if necessary

@Injectable({
  providedIn: 'root'
})
export class CricketPlayerService {
  // Replace with your actual backend endpoint for cricket players
  private backendUrl = 'https://ide-fddbbedbb327214235bfdebbddcaecone.premiumproject.examly.io/proxy/3001/cricketplayer';

  constructor(private http: HttpClient) { }

  // Get all cricket players
  getPlayers(): Observable<CricketPlayer[]> {
    return this.http.get<CricketPlayer[]>(this.backendUrl);
  }

  // Add a new cricket player
  addPlayer(player: CricketPlayer): Observable<CricketPlayer> {
    return this.http.post<CricketPlayer>(this.backendUrl, player);
  }

  // Delete a cricket player by ID
  deletePlayer(id: number): Observable<void> {
    const url = `${this.backendUrl}/${id}`;
    return this.http.delete<void>(url);
  }
}
