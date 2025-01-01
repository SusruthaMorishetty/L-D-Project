import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { BehaviorSubject, Observable } from 'rxjs';

export interface Course {
  id: number,
  title: string;
  description: string;
  contentUrl: string;
  
}

@Injectable({
  providedIn: 'root'
})
export class UserCourseService {

  private coursesSubject = new BehaviorSubject<Course[]>([]);
  courses$ = this.coursesSubject.asObservable();

  private url = 'http://localhost:8888/api';
  

  constructor(private http: HttpClient) { }

  getCoursesByTrainingProgram(trainingProgramId: number): Observable<Course[]> {
    return this.http.get<Course[]>(`${this.url}/trainingProgram/${trainingProgramId}/courses`);
  }

  // updateCourseStatus(userId: number, courseId: number, status: string): Observable<any> {
  //   return this.http.put(`${this.url}/progress/update/${userId}/${courseId}`, status);
  // }
}
