import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { ReviewerListComponent } from './reviewer-list.component';

describe('ReviewerListComponent', () => {
  let component: ReviewerListComponent;
  let fixture: ComponentFixture<ReviewerListComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ ReviewerListComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(ReviewerListComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
