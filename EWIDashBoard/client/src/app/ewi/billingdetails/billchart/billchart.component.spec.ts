import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { BillchartComponent } from './billchart.component';

describe('BillchartComponent', () => {
  let component: BillchartComponent;
  let fixture: ComponentFixture<BillchartComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ BillchartComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(BillchartComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
