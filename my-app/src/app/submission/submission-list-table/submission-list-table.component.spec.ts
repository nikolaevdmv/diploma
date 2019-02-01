import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { SubmissionListTableComponent } from './submission-list-table.component';

describe('SubmissionListTable', () => {
  let component: SubmissionListTableComponent;
  let fixture: ComponentFixture<SubmissionListTableComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ SubmissionListTableComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(SubmissionListTableComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
