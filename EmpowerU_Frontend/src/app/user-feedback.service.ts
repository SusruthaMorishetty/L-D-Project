import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';

@Injectable({
  providedIn: 'root'
})
export class UserFeedbackService {

  private apiUrl = 'http://localhost:8888/api/feedback'; // Replace with your API endpoint

  constructor(private http: HttpClient) { }

  private userId = sessionStorage.getItem('userId');
  private courseId = localStorage.getItem('courseId');

  // Method to submit feedback
  submitFeedback(feedbackData: any): Observable<any> {
    return this.http.post<any>(`${this.apiUrl}/user/${this.userId}/course/${this.courseId}`, feedbackData);
  }
}
