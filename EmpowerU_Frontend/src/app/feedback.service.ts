import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';

@Injectable({
  providedIn: 'root'
})
export class FeedbackService {

  private apiUrl = 'http://localhost:8888/api/feedback'; // Base API URL

  constructor(private http: HttpClient) {}

  // Get feedbacks for a specific user
  // getUserFeedbacks(userId: number): Observable<any[]> {
  //   return this.http.get<any[]>(`${this.apiUrl}/user/${userId}/feedbacks`);
  // }
  
  fetchAllFeedbacks(): Observable<any[]> {
    return this.http.get<any[]>(this.apiUrl);  // Get all feedbacks from backend
  }
}
