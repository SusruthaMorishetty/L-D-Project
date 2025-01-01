import { ComponentFixture, TestBed } from '@angular/core/testing';

import { AddTrainingProgramsComponent } from './add-training-programs.component';

describe('AddTrainingProgramsComponent', () => {
  let component: AddTrainingProgramsComponent;
  let fixture: ComponentFixture<AddTrainingProgramsComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      imports: [AddTrainingProgramsComponent]
    })
    .compileComponents();

    fixture = TestBed.createComponent(AddTrainingProgramsComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
