export interface CricketPlayer {
    id?: number;
    name: string;
    age: number;
    team: string;
    position: string; // Batsman, Bowler, All-rounder, Wicketkeeper
    battingStyle: string;
    bowlingStyle: string;
    totalRuns?: number;
    totalWickets?: number;
    totalMatches?: number;
  }
  