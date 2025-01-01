import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';
import { UserDetails } from './model/UserDetails';

@Injectable({
  providedIn: 'root'
})
export class UserService {

  constructor(private http: HttpClient) { }

  private apiUrl = 'http://localhost:8888/api';

  // Get user details by id (JWT token automatically included via interceptor)
  getUserDetails(id: number): Observable<UserDetails> {
    return this.http.get<UserDetails>(`${this.apiUrl}/user/${id}`);
  }
}


