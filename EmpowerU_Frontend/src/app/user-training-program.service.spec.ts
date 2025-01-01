import { TestBed } from '@angular/core/testing';

import { UserTrainingProgramService } from './user-training-program.service';

describe('UserTrainingProgramService', () => {
  let service: UserTrainingProgramService;

  beforeEach(() => {
    TestBed.configureTestingModule({});
    service = TestBed.inject(UserTrainingProgramService);
  });

  it('should be created', () => {
    expect(service).toBeTruthy();
  });
});
