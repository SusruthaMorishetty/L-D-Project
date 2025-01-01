import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { BehaviorSubject, catchError, Observable, pipe, tap, throwError } from 'rxjs';

export interface Course {
  id:number;
  title: string;
  description: string;
  contentUrl: string;
}

@Injectable({
  providedIn: 'root'
})
export class CourseService {

  private coursesSubject = new BehaviorSubject<Course[]>([]);
  courses$ = this.coursesSubject.asObservable();

  private baseUrl = 'http://localhost:8888/api/trainingProgram/course';
  private url = 'http://localhost:8888/api';

  constructor(private http: HttpClient) { }

  private trainingProgramId = localStorage.getItem('trainingProgramId');
  // private course
  
  

  addCourse(course: Course): Observable<Course> {
    // Send the course data to the backend via HTTP POST
    // const trainingProgramId = this.trainingPrograms?.indexOf(0);
    console.log(this.trainingProgramId);
    
    console.log(course);
    return this.http.post<Course>(`${this.baseUrl}/${this.trainingProgramId}`, course)

      .pipe(
        tap(newCourse => {
          console.log(newCourse);
          // After successfully adding the course, update the local state
          const currentCourses = this.coursesSubject.getValue();
          this.coursesSubject.next([...currentCourses, newCourse]);
        }),
        catchError(error => {
          console.error('Error adding course', error);
          // Handle error appropriately, for now we rethrow it
          return throwError(() => error);
        })
      );
  }

  getCoursesByTrainingProgram(trainingProgramId: number): Observable<Course[]> {
    return this.http.get<Course[]>(`${this.url}/trainingProgram/${trainingProgramId}/courses`);
  }

  deleteCourse(courseId: number): Observable<void> {
    return this.http.delete<void>(`${this.baseUrl}/${courseId}`)
      .pipe(
        tap(() => {
          // Remove the course from the local state using the ID
          const currentCourses = this.coursesSubject.getValue();
          const updatedCourses = currentCourses.filter(c => c.id !== courseId);
          this.coursesSubject.next(updatedCourses);
        }),
        catchError(error => {
          console.error('Error deleting course', error);
          return throwError(() => error);
        })
      );
  }
  
  
}
