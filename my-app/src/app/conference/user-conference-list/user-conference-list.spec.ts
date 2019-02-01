import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { ConfListComponent } from './user-conference-list.component';

describe('ConfListComponent', () => {
  let component: ConfListComponent;
  let fixture: ComponentFixture<ConfListComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ ConfListComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(ConfListComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
