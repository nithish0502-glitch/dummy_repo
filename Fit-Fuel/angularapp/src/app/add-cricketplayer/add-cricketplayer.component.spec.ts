import { ComponentFixture, TestBed } from '@angular/core/testing';
import { ReactiveFormsModule, FormBuilder } from '@angular/forms';
import { AddCricketPlayerComponent } from './add-cricketplayer.component';
import { CricketPlayerService } from '../services/cricketplayer.service';
import { of } from 'rxjs';
import { Router } from '@angular/router';
import { DebugElement } from '@angular/core';
import { HttpClientTestingModule } from '@angular/common/http/testing';
import { RouterTestingModule } from '@angular/router/testing';

describe('AddCricketPlayerComponent', () => {
  let component: AddCricketPlayerComponent;
  let fixture: ComponentFixture<AddCricketPlayerComponent>;
  let service: CricketPlayerService;
  let debugElement: DebugElement;
  let formBuilder: FormBuilder;
  let router: Router;

  beforeEach(() => {
    TestBed.configureTestingModule({
      declarations: [AddCricketPlayerComponent],
      imports: [ReactiveFormsModule, HttpClientTestingModule, RouterTestingModule],
      providers: [CricketPlayerService],
    });

    formBuilder = TestBed.inject(FormBuilder);
    fixture = TestBed.createComponent(AddCricketPlayerComponent);
    component = fixture.componentInstance;
    service = TestBed.inject(CricketPlayerService);
    router = TestBed.inject(Router);
    spyOn(router, 'navigateByUrl').and.returnValue(Promise.resolve(true));
    fixture.detectChanges();
  });

  fit('should_create_AddCricketPlayerComponent', () => {
    expect(component).toBeTruthy();
  });

  fit('should_add_a_new_cricket_player_when_form_is_valid', () => {
    const mockPlayer = {
      name: 'Virat Kohli',
      age: 35,
      team: 'India',
      position: 'Batsman',
      battingStyle: 'Right-hand bat',
      bowlingStyle: 'Right-arm medium',
      totalRuns: 12000,
      totalWickets: 5,
      totalMatches: 275
    };

    spyOn(service, 'addPlayer').and.returnValue(of(mockPlayer));
    component.playerForm.setValue(mockPlayer);

    component.addNewPlayer();

    expect(component.playerForm.valid).toBeTrue();
    expect(service.addPlayer).toHaveBeenCalledWith(mockPlayer);
    expect(router.navigateByUrl).toHaveBeenCalledWith('/players');
  });

  fit('should_require_all_mandatory_form_fields', () => {
    const form = component.playerForm;

    form.setValue({
      name: '',
      age: '',
      team: '',
      position: '',
      battingStyle: '',
      bowlingStyle: '',
      totalRuns: '',
      totalWickets: '',
      totalMatches: ''
    });

    expect(form.valid).toBeFalse();
    expect(form.get('name')?.hasError('required')).toBeTrue();
    expect(form.get('age')?.hasError('required')).toBeTrue();
    expect(form.get('team')?.hasError('required')).toBeTrue();
    expect(form.get('position')?.hasError('required')).toBeTrue();
    expect(form.get('battingStyle')?.hasError('required')).toBeTrue();
    expect(form.get('bowlingStyle')?.hasError('required')).toBeTrue();
  });

  fit('should_accept_valid_form_even_if_optional_stats_are_missing', () => {
    const form = component.playerForm;

    form.setValue({
      name: 'Jasprit Bumrah',
      age: 31,
      team: 'India',
      position: 'Bowler',
      battingStyle: 'Right-hand bat',
      bowlingStyle: 'Right-arm fast',
      totalRuns: '',
      totalWickets: '',
      totalMatches: ''
    });

    expect(form.valid).toBeTrue();
  });
});
