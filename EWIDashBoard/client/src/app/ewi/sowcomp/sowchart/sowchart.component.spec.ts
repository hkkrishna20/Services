import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { SowchartComponent } from './sowchart.component';

describe('SowchartComponent', () => {
  let component: SowchartComponent;
  let fixture: ComponentFixture<SowchartComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ SowchartComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(SowchartComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
