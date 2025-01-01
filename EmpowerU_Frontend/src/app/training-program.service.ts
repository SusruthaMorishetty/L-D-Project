import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { BehaviorSubject, catchError, Observable, tap, throwError } from 'rxjs';

export interface TrainingProgram {
  id: number;
  title: string;
  description: string;
}

@Injectable({
  providedIn: 'root'
})
export class TrainingProgramService {

  private baseUrl = 'http://localhost:8888/api/trainingProgram'; 

  private trainingProgramsSubject =new BehaviorSubject<TrainingProgram[]>([]);
  trainingPrograms$=this.trainingProgramsSubject.asObservable();

  constructor(private http: HttpClient) {
    // Attempt to load programs on service initialization
    this.loadStoredPrograms();
  }

  // Method to load programs from local storage or initial fetch
  private loadStoredPrograms(): void {
    const storedPrograms = localStorage.getItem('trainingPrograms');
    if (storedPrograms) {
      this.trainingProgramsSubject.next(JSON.parse(storedPrograms));
    }
    
    // Always fetch from backend to ensure latest data
    this.fetchTrainingPrograms();
  }

  fetchTrainingPrograms(): void {
    this.http.get<TrainingProgram[]>(this.baseUrl)
      .pipe(
        tap((programs: TrainingProgram[]) => {
          console.log('Fetched programs:', programs);
          // Update local storage
          localStorage.setItem('trainingPrograms', JSON.stringify(programs));
          // Update the subject with fetched programs
          this.trainingProgramsSubject.next(programs);
        }),
        catchError(error => {
          console.error('Error fetching training programs', error);
          // If fetch fails, try to use stored programs
          const storedPrograms = localStorage.getItem('trainingPrograms');
          if (storedPrograms) {
            this.trainingProgramsSubject.next(JSON.parse(storedPrograms));
          }
          return throwError(() => error);
        })
      )
      .subscribe();
  }


  addTrainingProgram(trainingProgram: TrainingProgram): Observable<TrainingProgram> {
    console.log('Attempting to add program:', trainingProgram);
    return this.http.post<TrainingProgram>(this.baseUrl, trainingProgram)
      .pipe(
        tap(newProgram => {
          // Update local state after successful backend call
           console.log('Program added successfully:', newProgram);
          const currentPrograms = this.trainingProgramsSubject.value;
          this.trainingProgramsSubject.next([...currentPrograms, newProgram]);
        }),
        catchError(error => {
          console.error('Error adding training program', error);
          throw error; // Rethrow to allow component to handle
        })
      );
  }

  deleteTrainingProgram(id: number): Observable<void> {
    console.log('Attempting to delete program with id:', id);
    return this.http.delete<void>(`${this.baseUrl}/${id}`)
      .pipe(
        tap(() => {
          // Remove the deleted program from the local state
          const currentPrograms = this.trainingProgramsSubject.value;
          const updatedPrograms = currentPrograms.filter(program => program.id !== id);
          this.trainingProgramsSubject.next(updatedPrograms);

          // Update local storage with the new list
          localStorage.setItem('trainingPrograms', JSON.stringify(updatedPrograms));
          console.log('Program deleted successfully');
        }),
        catchError(error => {
          console.error('Error deleting training program', error);
          throw error; // Rethrow to allow component to handle
        })
      );
  }

  
  





  // addTrainingProgram(trainingProgram:TrainingProgram):void{
  //   const currentPrograms=this.trainingProgramsSubject.value;
  //   this.trainingProgramsSubject.next([...currentPrograms,trainingProgram]);
  // }
}
