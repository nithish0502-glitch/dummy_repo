import { ComponentFixture, TestBed } from '@angular/core/testing';

import { MyHistoryComponent } from './my-history.component';
import { HttpClientModule } from '@angular/common/http';

describe('MyHistoryComponent', () => {
  let component: MyHistoryComponent;
  let fixture: ComponentFixture<MyHistoryComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      imports: [HttpClientModule],
      declarations: [ MyHistoryComponent ]
    })
    .compileComponents();
  });

  beforeEach(() => {
    fixture = TestBed.createComponent(MyHistoryComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  fit('Frontend_day28_should_create_MyHistory_Component', () => {
    expect(component).toBeTruthy();
  });
});
