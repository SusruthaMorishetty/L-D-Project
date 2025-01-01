import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { BehaviorSubject, catchError, tap, throwError } from 'rxjs';

export interface TrainingProgram {
  id: number;
  title: string;
  description: string;
}

@Injectable({
  providedIn: 'root'
})
export class UserTrainingProgramService {

  private baseUrl = 'http://localhost:8888/api/trainingProgram/user'; 

  private trainingProgramsSubject =new BehaviorSubject<TrainingProgram[]>([]);
  trainingPrograms$=this.trainingProgramsSubject.asObservable();

  constructor(private http: HttpClient) {
    // Attempt to load programs on service initialization
    this.fetchTrainingPrograms();
  }

  private userId = sessionStorage.getItem('userId');

  fetchTrainingPrograms() {
    this.http.get<TrainingProgram[]>(`${this.baseUrl}/${this.userId}`)
      .pipe(
        tap((programs) => {
          // On success, update the subject with the fetched training programs
          this.trainingProgramsSubject.next(programs);
        }),
        catchError((error) => {
          // Log or handle the error as needed
          console.error('Error fetching training programs:', error);
          return throwError(() => new Error('Failed to fetch training programs.'));
        })
      )
      .subscribe();
  }

  // Method to load programs from local storage or initial fetch
  // private loadStoredPrograms(): void {
  //   const storedPrograms = localStorage.getItem('trainingPrograms');
  //   if (storedPrograms) {
  //     this.trainingProgramsSubject.next(JSON.parse(storedPrograms));
  //   }
    
    // Always fetch from backend to ensure latest data
    
  

  // fetchTrainingPrograms(): void {
  //   this.http.get<TrainingProgram[]>(this.baseUrl)
  //     .pipe(
  //       tap((programs: TrainingProgram[]) => {
  //         console.log('Fetched programs:', programs);
  //         // Update local storage
  //         localStorage.setItem('trainingPrograms', JSON.stringify(programs));
  //         // Update the subject with fetched programs
  //         this.trainingProgramsSubject.next(programs);
  //       }),
  //       catchError(error => {
  //         console.error('Error fetching training programs', error);
  //         // If fetch fails, try to use stored programs
  //         const storedPrograms = localStorage.getItem('trainingPrograms');
  //         if (storedPrograms) {
  //           this.trainingProgramsSubject.next(JSON.parse(storedPrograms));
  //         }
  //         return throwError(() => error);
  //       })
  //     )
  //     .subscribe();
  // }
}
