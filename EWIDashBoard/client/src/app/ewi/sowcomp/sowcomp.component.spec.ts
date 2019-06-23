import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { SowcompComponent } from './sowcomp.component';

describe('SowcompComponent', () => {
  let component: SowcompComponent;
  let fixture: ComponentFixture<SowcompComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ SowcompComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(SowcompComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
