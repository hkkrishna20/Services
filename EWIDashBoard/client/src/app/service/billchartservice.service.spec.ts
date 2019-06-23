import { TestBed } from '@angular/core/testing';

import { BillchartserviceService } from './billchartservice.service';

describe('BillchartserviceService', () => {
  beforeEach(() => TestBed.configureTestingModule({}));

  it('should be created', () => {
    const service: BillchartserviceService = TestBed.get(BillchartserviceService);
    expect(service).toBeTruthy();
  });
});
