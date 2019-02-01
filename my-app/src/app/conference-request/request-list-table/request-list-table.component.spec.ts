import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { RequestListTableComponent } from './request-list-table.component';

describe('RequestsTableComponent', () => {
  let component: RequestListTableComponent;
  let fixture: ComponentFixture<RequestListTableComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ RequestListTableComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(RequestListTableComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
