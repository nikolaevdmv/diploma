import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { AddNewReviewerTableComponent } from './add-new-reviewer-table.component';

describe('AddNewReviewerTableComponent', () => {
  let component: AddNewReviewerTableComponent;
  let fixture: ComponentFixture<AddNewReviewerTableComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ AddNewReviewerTableComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(AddNewReviewerTableComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
