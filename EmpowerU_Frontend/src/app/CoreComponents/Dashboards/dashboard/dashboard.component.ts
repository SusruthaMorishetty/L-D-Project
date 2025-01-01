import { CommonModule } from '@angular/common';
import { Component, OnInit } from '@angular/core';
import { RouterModule } from '@angular/router';
import { Observable } from 'rxjs';
import { AllcoursesService, Course } from '../../../allcourses.service';
import { TraineesService, User } from '../../../trainees.service';
import { TrainingProgram, TrainingProgramService } from '../../../training-program.service';
import { FeedbackService } from '../../../feedback.service'; // Import FeedbackService
import { Feedback } from '../../../feedback/feedback.component';

@Component({
  selector: 'app-dashboard',
  standalone: true,
  imports: [RouterModule, CommonModule],
  templateUrl: './dashboard.component.html',
  styleUrls: ['./dashboard.component.css']
})
export class DashboardComponent implements OnInit {
  trainingPrograms$: Observable<TrainingProgram[]>;
  courses$: Observable<Course[]>;
  users$: Observable<User[]>; // Observable for users
  feedbacks$: Observable<Feedback[]>; // Observable for feedbacks

  totalPrograms: number = 0;
  totalCourses: number = 0;
  totalUsers: number = 0; // Property to hold the total number of users
  totalFeedbacks: number = 0; // Property to hold the total number of feedbacks

  constructor(
    private trainingProgramService: TrainingProgramService,
    private allcoursesService: AllcoursesService,
    private traineesService: TraineesService, // Inject TraineesService
    private feedbackService: FeedbackService // Inject FeedbackService
  ) {
    this.trainingPrograms$ = this.trainingProgramService.trainingPrograms$;
    this.courses$ = this.allcoursesService.getAllCourses();
    this.users$ = this.traineesService.getAllUsers(); // Fetch users observable
    this.feedbacks$ = this.feedbackService.fetchAllFeedbacks(); // Observable for feedbacks
  }

  ngOnInit(): void {
    // Subscribe to the observable to get the total count of training programs
    this.trainingPrograms$.subscribe((programs) => {
      this.totalPrograms = programs.length;
    });

    // Subscribe to the observable to get the total count of courses
    this.courses$.subscribe((courses) => {
      this.totalCourses = courses.length;
    });

    // Subscribe to the observable to get the total count of users
    this.users$.subscribe((users) => {
      this.totalUsers = users.filter(user => user.name != null && user.name.trim() !== '').length; // Filter out invalid names
    });

    // Subscribe to the observable to get the total count of feedbacks
    this.feedbacks$.subscribe((feedbacks) => {
      this.totalFeedbacks = feedbacks.length; // Update the total count of feedbacks
    });
  }
}
