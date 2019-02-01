import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { NewConferenceRequestComponent } from './new-conference-request.component';

describe('NewConferenceRequestComponent', () => {
  let component: NewConferenceRequestComponent;
  let fixture: ComponentFixture<NewConferenceRequestComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ NewConferenceRequestComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(NewConferenceRequestComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
