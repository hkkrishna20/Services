import { TestBed } from '@angular/core/testing';

import { SowserviceService } from './sowservice.service';

describe('SowserviceService', () => {
  beforeEach(() => TestBed.configureTestingModule({}));

  it('should be created', () => {
    const service: SowserviceService = TestBed.get(SowserviceService);
    expect(service).toBeTruthy();
  });
});
