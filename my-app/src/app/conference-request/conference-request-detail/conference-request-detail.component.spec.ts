import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { ConferenceRequestDetailComponent } from './conference-request-detail.component';

describe('ConferenceRequestDetailComponent', () => {
  let component: ConferenceRequestDetailComponent;
  let fixture: ComponentFixture<ConferenceRequestDetailComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ ConferenceRequestDetailComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(ConferenceRequestDetailComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
