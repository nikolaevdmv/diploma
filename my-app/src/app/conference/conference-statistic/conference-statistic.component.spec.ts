import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { ConferenceStatisticComponent } from './conference-statistic.component';

describe('ConferenceStatisticComponent', () => {
  let component: ConferenceStatisticComponent;
  let fixture: ComponentFixture<ConferenceStatisticComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ ConferenceStatisticComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(ConferenceStatisticComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
