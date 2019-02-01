import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { ReviewerListTableComponent } from './reviewer-list-table.component';

describe('ReviewerListTableComponent', () => {
  let component: ReviewerListTableComponent;
  let fixture: ComponentFixture<ReviewerListTableComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ ReviewerListTableComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(ReviewerListTableComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
