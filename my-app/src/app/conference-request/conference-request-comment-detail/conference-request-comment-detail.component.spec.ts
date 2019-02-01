import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { ConferenceRequestCommentDetailComponent } from './conference-request-comment-detail.component';

describe('ConferenceRequestCommentDetailComponent', () => {
  let component: ConferenceRequestCommentDetailComponent;
  let fixture: ComponentFixture<ConferenceRequestCommentDetailComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ ConferenceRequestCommentDetailComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(ConferenceRequestCommentDetailComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
