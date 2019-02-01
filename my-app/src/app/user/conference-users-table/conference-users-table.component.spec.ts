import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { ConferenceUsersTableComponent } from './conference-users-table.component';

describe('ConferenceUsersTableComponent', () => {
  let component: ConferenceUsersTableComponent;
  let fixture: ComponentFixture<ConferenceUsersTableComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ ConferenceUsersTableComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(ConferenceUsersTableComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
