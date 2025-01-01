import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';

export interface User {
  id: number;
  name: string;
  role: string;
}

@Injectable({
  providedIn: 'root'
})
export class TraineesService {

  private apiUrl = 'http://localhost:8888/api/user'; 
  private programUrl = 'http://localhost:8888/api/trainingProgram';

  constructor(private http: HttpClient) { }

  // Fetch all users from the backend
  getAllUsers(): Observable<User[]> {
    return this.http.get<User[]>(this.apiUrl);
  }

  addUserToProgram(userId: number, programId: number): Observable<any> {
    return this.http.post(`${this.programUrl}/${programId}/${userId}`,{});
  }

  // Remove user from a program
  removeUserFromProgram(userId: number, programId: number): Observable<any> {
    return this.http.delete(`${this.programUrl}/${programId}/${userId}`);
  }
}
