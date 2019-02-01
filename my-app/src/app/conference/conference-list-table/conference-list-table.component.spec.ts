import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { ConferenceListTableComponent } from './conference-list-table.component';

describe('ConferencesTableComponent', () => {
  let component: ConferenceListTableComponent;
  let fixture: ComponentFixture<ConferenceListTableComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ ConferenceListTableComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(ConferenceListTableComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
