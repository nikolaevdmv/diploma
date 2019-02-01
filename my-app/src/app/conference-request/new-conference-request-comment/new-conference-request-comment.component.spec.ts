import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { NewConferenceRequestCommentComponent } from './new-conference-request-comment.component';

describe('ConferenceRequestFormComponent', () => {
  let component: NewConferenceRequestCommentComponent;
  let fixture: ComponentFixture<NewConferenceRequestCommentComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ NewConferenceRequestCommentComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(NewConferenceRequestCommentComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
