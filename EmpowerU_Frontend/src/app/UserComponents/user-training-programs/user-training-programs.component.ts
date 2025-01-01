import { CommonModule } from '@angular/common';
import { Component, OnInit } from '@angular/core';
import { FormsModule } from '@angular/forms';
import { RouterModule } from '@angular/router';
import { TrainingProgram, TrainingProgramService } from '../../training-program.service';
import { UserTrainingProgramService } from '../../user-training-program.service';

@Component({
  selector: 'app-user-training-programs',
  standalone: true,
  imports: [CommonModule,FormsModule,RouterModule],
  templateUrl: './user-training-programs.component.html',
  styleUrl: './user-training-programs.component.css'
})
export class UserTrainingProgramsComponent implements OnInit{
  trainingPrograms:TrainingProgram[]=[];

  constructor(private userTrainingProgramService:UserTrainingProgramService){}
  ngOnInit(): void {
    this.userTrainingProgramService.trainingPrograms$.subscribe((programs) => {
      this.trainingPrograms = programs; // Update the trainingPrograms list when the service emits new data
    });
  }

  saveId(trainingProgramId: number): void {
    // Store the trainingProgramId in localStorage
    localStorage.setItem('trainingProgramId', trainingProgramId.toString());
  }

}
