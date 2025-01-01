import { CommonModule } from '@angular/common';
import { Component, OnInit } from '@angular/core';
import { FormsModule } from '@angular/forms';
import { RouterModule } from '@angular/router';
import { ProgressService } from '../progress.service';

@Component({
  selector: 'app-progress',
  standalone: true,
  imports: [CommonModule,FormsModule,RouterModule],
  templateUrl: './progress.component.html',
  styleUrl: './progress.component.css'
})
export class ProgressComponent implements OnInit{
  progresses: any[] = [];

  constructor(private progressService: ProgressService) {}

  ngOnInit(): void {
    this.getAllProgress();
  }

  // Fetch all progress data from the backend
  getAllProgress(): void {
    this.progressService.getAllProgress().subscribe(
      (data) => {
        this.progresses = data;
      },
      (error) => {
        console.error('Error fetching progress data:', error);
      }
    );
  }

  // Optionally, you can implement methods to delete or update progress
  deleteProgress(progressId: number): void {
    this.progressService.deleteProgress(progressId).subscribe(
      () => {
        this.progresses = this.progresses.filter(p => p.progressId !== progressId);
      },
      (error) => {
        console.error('Error deleting progress:', error);
      }
    );
  }
}


 
