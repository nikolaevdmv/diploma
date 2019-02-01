import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { ConferenceUserListComponent } from './conference-user-list.component';

describe('ConferenceUserListComponent', () => {
  let component: ConferenceUserListComponent;
  let fixture: ComponentFixture<ConferenceUserListComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ ConferenceUserListComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(ConferenceUserListComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
