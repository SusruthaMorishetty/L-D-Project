import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';

@Injectable({
  providedIn: 'root'
})
export class ProgressService {

  private apiUrl = 'http://localhost:8888/api/progress'; // Adjust as necessary

  constructor(private http: HttpClient) {}

  // Get all progress records
  getAllProgress(): Observable<any[]> {
    return this.http.get<any[]>(`${this.apiUrl}`);
  }

  // Get progress by specific progress ID
  getProgressById(progressId: number): Observable<any> {
    return this.http.get<any>(`${this.apiUrl}/${progressId}`);
  }

  // Get progress by user ID
  getProgressForUser(userId: number): Observable<any> {
    return this.http.get<any>(`${this.apiUrl}/user/${userId}`);
  }

  // Get progress for a specific user and course
  getProgressByUserAndCourse(userId: number, courseId: number): Observable<any> {
    return this.http.get<any>(`${this.apiUrl}/user/${userId}/course/${courseId}`);
  }

  // Create a new progress record
  createProgress(progress: any): Observable<any> {
    return this.http.post<any>(this.apiUrl, progress);
  }

  // Update an existing progress record
  updateProgress(progressId: number, progress: any): Observable<any> {
    return this.http.put<any>(`${this.apiUrl}/${progressId}`, progress);
  }

  // Delete a progress record
  deleteProgress(progressId: number): Observable<void> {
    return this.http.delete<void>(`${this.apiUrl}/${progressId}`);
  }
}
