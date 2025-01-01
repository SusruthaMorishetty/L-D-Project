import { CommonModule } from '@angular/common';
import { Component } from '@angular/core';
import { FormBuilder, FormGroup, FormsModule, ReactiveFormsModule, Validators } from '@angular/forms';
import { RouterModule } from '@angular/router';
import { UserFeedbackService } from '../../user-feedback.service';

@Component({
  selector: 'app-user-feedback',
  standalone: true,
  imports: [RouterModule,CommonModule,ReactiveFormsModule,FormsModule],
  templateUrl: './user-feedback.component.html',
  styleUrl: './user-feedback.component.css'
})
export class UserFeedbackComponent {

  feedbackForm!: FormGroup ;

  constructor(
    private fb: FormBuilder,
    private feedbackService: UserFeedbackService
  ) { }

  ngOnInit(): void {
    // Initialize the feedback form with validations
    this.feedbackForm = this.fb.group({
      rating: ['', Validators.required],
      description: ['', Validators.required]
    });
  }

  // Submit the feedback form
  onSubmit(): void {
    // Mark all fields as touched to trigger validation display
    this.markFormGroupTouched(this.feedbackForm);

    if (this.feedbackForm.valid) {
      const feedbackData = this.feedbackForm.value;
      console.log('Form Submitted:', this.feedbackForm.value);
      
      // Call the feedback service to submit the feedback
      this.feedbackService.submitFeedback(feedbackData).subscribe({
        next: (response: any) => {
          // Handle successful response 
          console.log('Feedback submitted successfully', response);
          this.feedbackForm.reset(); // Reset the form after submission
        },
        error: (error: any) => {
          // Handle error response
          console.error('Error submitting feedback', error);
          // Optionally show an error message to the user
        }
      });
    } else {
      // Optionally handle invalid form submission
      console.log('Form is invalid', this.feedbackForm.errors);
    }
  }
  private markFormGroupTouched(formGroup: FormGroup) {
    Object.values(formGroup.controls).forEach(control => {
      control.markAsTouched();

      if (control instanceof FormGroup) {
        this.markFormGroupTouched(control);
      }
    });
  }

}
