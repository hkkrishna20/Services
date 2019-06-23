import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { EwiComponent } from './ewi.component';

describe('EwiComponent', () => {
  let component: EwiComponent;
  let fixture: ComponentFixture<EwiComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ EwiComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(EwiComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
