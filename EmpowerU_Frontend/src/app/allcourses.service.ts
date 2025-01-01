import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';

export interface Course {
  title: string;
  description: string;
  url: string;
}

@Injectable({
  providedIn: 'root'
})
export class AllcoursesService {
  private apiUrl = 'http://localhost:8888/api/trainingProgram/course';  
  constructor(private http: HttpClient) { }

  // Method to fetch all courses
  getAllCourses(): Observable<Course[]> {
    return this.http.get<Course[]>(this.apiUrl);  
  }
  // deleteCourse(courseId: number): Observable<void> {
  //   return this.http.delete<void>(`${this.apiUrl}/${courseId}`);  // DELETE request to delete a course by its ID
  // }
}
