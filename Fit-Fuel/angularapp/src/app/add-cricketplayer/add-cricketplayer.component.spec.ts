import { ComponentFixture, TestBed } from '@angular/core/testing';

import { AddCricketplayerComponent } from './add-cricketplayer.component';

describe('AddCricketplayerComponent', () => {
  let component: AddCricketplayerComponent;
  let fixture: ComponentFixture<AddCricketplayerComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ AddCricketplayerComponent ]
    })
    .compileComponents();
  });

  beforeEach(() => {
    fixture = TestBed.createComponent(AddCricketplayerComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
