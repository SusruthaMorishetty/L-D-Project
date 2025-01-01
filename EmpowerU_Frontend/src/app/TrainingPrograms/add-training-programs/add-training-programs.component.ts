import { CommonModule } from '@angular/common';
import { Component } from '@angular/core';
import { FormBuilder, FormGroup, ReactiveFormsModule, Validators } from '@angular/forms';
import { Router, RouterModule } from '@angular/router';
import { TrainingProgramService } from '../../training-program.service';

@Component({
  selector: 'app-add-training-programs',
  standalone: true,
  imports: [ReactiveFormsModule, CommonModule, RouterModule],
  templateUrl: './add-training-programs.component.html',
  styleUrl: './add-training-programs.component.css'
})
export class AddTrainingProgramsComponent {
  trainingProgramForm: FormGroup;

  constructor(private fb: FormBuilder,private trainingProgramService:TrainingProgramService,private router:Router) {
    this.trainingProgramForm = this.fb.group({
      title: ['', [Validators.required, Validators.minLength(5)]],
      description: ['', [Validators.required, Validators.minLength(10)]]
    });
  }

  onSubmit(): void {
    if (this.trainingProgramForm.valid) {
      const newTrainingProgram = this.trainingProgramForm.value;
      this.trainingProgramService.addTrainingProgram(newTrainingProgram).subscribe(); // Add new program to the service
      this.trainingProgramForm.reset();
      alert('Training Program Created Successfully!');
      this.trainingProgramForm.reset();
      this.router.navigate(['/training-programs'])
    } else {
      alert('Please fill in the required fields correctly.');
    }
  }
}
