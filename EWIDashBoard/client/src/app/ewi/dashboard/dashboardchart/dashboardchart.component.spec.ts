import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { DashboardchartComponent } from './dashboardchart.component';

describe('DashboardchartComponent', () => {
  let component: DashboardchartComponent;
  let fixture: ComponentFixture<DashboardchartComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ DashboardchartComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(DashboardchartComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
