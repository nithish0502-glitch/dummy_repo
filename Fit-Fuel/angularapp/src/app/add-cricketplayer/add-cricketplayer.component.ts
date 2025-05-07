import { Component, OnInit } from '@angular/core';
import { FormBuilder, FormGroup, Validators } from '@angular/forms';
import { Router } from '@angular/router';
import { CricketPlayerService } from '../services/cricketplayer.service';

@Component({
  selector: 'app-add-cricket-player',
  templateUrl: './add-cricketplayer.component.html',
  styleUrls: ['./add-cricketplayer.component.css']
})
export class AddCricketPlayerComponent implements OnInit {

  playerForm: FormGroup;

  constructor(
    private formBuilder: FormBuilder,
    private cricketPlayerService: CricketPlayerService,
    private router: Router
  ) {
    this.playerForm = this.formBuilder.group({
      name: ['', Validators.required],
      age: ['', Validators.required],
      team: ['', Validators.required],
      position: ['', Validators.required],
      battingStyle: ['', Validators.required],
      bowlingStyle: ['', Validators.required],
      totalRuns: [''],
      totalWickets: [''],
      totalMatches: ['']
    });
  }

  ngOnInit(): void {}

  addNewPlayer() {
    if (this.playerForm.valid) {
      console.log(this.playerForm.value);
      this.cricketPlayerService.addPlayer(this.playerForm.value)
        .subscribe(
          (res) => {
            console.log('Player added:', res);
            this.router.navigateByUrl('/players');
          },
          (err) => {
            console.error('Error adding player:', err);
          }
        );
    }
  }
}

