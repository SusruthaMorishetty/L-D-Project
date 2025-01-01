import { CommonModule } from '@angular/common';
import { Component, OnInit } from '@angular/core';
import { Router, RouterModule } from '@angular/router';
import { Observable } from 'rxjs'; // Add this import
import { TrainingProgram, TrainingProgramService } from '../../training-program.service';
import { CoursesComponent } from '../courses/courses.component';

@Component({
  selector: 'app-training-programs',
  standalone: true,
  imports: [CommonModule, CoursesComponent, RouterModule],
  templateUrl: './training-programs.component.html',
  styleUrl: './training-programs.component.css'
})
export class TrainingProgramsComponent implements OnInit {

 

  imagePaths: string[] = [
    'assets/cardImages/T1.jpg',
    'assets/cardImages/T2.jpg',
    'assets/cardImages/T3.jpg',
    'assets/cardImages/T4.jpg',
    'assets/cardImages/T5.jpg',
    'assets/cardImages/T6.jpg',
    'assets/cardImages/T7.jpg',
    'assets/cardImages/T8.jpg',
    'assets/cardImages/T9.jpg',
    'assets/cardImages/T10.jpg'
  ];

  randomImages: { [key: number]: string } = {};

  // Convert to observable
  trainingPrograms$: Observable<TrainingProgram[]>;

  constructor(private trainingProgramService: TrainingProgramService, private router:Router) {
    // Initialize the observable directly from the service
    this.trainingPrograms$ = this.trainingProgramService.trainingPrograms$;

      // Pre-map random images to training programs
      this.trainingPrograms$.subscribe((programs) => {
        programs.forEach((program) => {
          this.randomImages[program.id] =
            this.imagePaths[Math.floor(Math.random() * this.imagePaths.length)];
        });
      });
  }

  ngOnInit(): void {
    // Fetch training programs when component initializes
    this.trainingProgramService.fetchTrainingPrograms();
    
  }

  saveId(trainingProgramId: number): void {
    // Store the trainingProgramId in localStorage
    localStorage.setItem('trainingProgramId', trainingProgramId.toString());
  }

  deleteProgram(programId: number): void {
    if (confirm('Are you sure you want to delete this training program?')) {
      this.trainingProgramService.deleteTrainingProgram(programId).subscribe(
        () => {
          console.log('Program deleted successfully');
        },
        (error) => {
          console.error('Error deleting program:', error);
        }
      );
    }
  }
  AddTrainineeToProgram(programId: number): void {
    this.router.navigate(['/trainees'], { queryParams: { programId } });
  }
  
}