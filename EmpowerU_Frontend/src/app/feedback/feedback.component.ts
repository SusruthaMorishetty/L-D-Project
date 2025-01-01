import { CommonModule } from '@angular/common';
import { Component } from '@angular/core';
import { FormsModule } from '@angular/forms';
import { RouterModule } from '@angular/router';
import { FeedbackService } from '../feedback.service';

export interface Feedback {
  courseName: string;
  rating: string;
  description: string;
}

@Component({
  selector: 'app-feedback',
  standalone: true,
  imports: [CommonModule,RouterModule,FormsModule],
  templateUrl: './feedback.component.html',
  styleUrl: './feedback.component.css'
})
export class FeedbackComponent {
  feedbacks: Feedback[] = [];

  constructor(private feedbackService: FeedbackService) {}

  ngOnInit(): void {
    this.fetchAllFeedbacks();
  }

  // fetchUserFeedbacks(): void {
  //   const userId = localStorage.getItem('userId'); // Get the userId from localStorage or route params
  //   if (userId) {
  //     this.feedbackService.getUserFeedbacks(Number(userId)).subscribe(
  //       (feedbacks: Feedback[]) => {
  //         this.feedbacks = feedbacks;
  //       },
  //       error => {
  //         console.error('Error fetching feedbacks', error);
  //       }
  //     );
  //   }
  // }

  fetchAllFeedbacks(): void {
    this.feedbackService.fetchAllFeedbacks().subscribe(
      (feedbacks: Feedback[]) => {
        this.feedbacks = feedbacks;  // Assign the fetched feedbacks to the local variable
      },
      error => {
        console.error('Error fetching all feedbacks', error);
      }
    );
  }

}
