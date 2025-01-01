import { ComponentFixture, TestBed } from '@angular/core/testing';

import { UserTrainingProgramsComponent } from './user-training-programs.component';

describe('UserTrainingProgramsComponent', () => {
  let component: UserTrainingProgramsComponent;
  let fixture: ComponentFixture<UserTrainingProgramsComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      imports: [UserTrainingProgramsComponent]
    })
    .compileComponents();

    fixture = TestBed.createComponent(UserTrainingProgramsComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
