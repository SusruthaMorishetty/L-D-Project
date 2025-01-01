import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';
import { LoginResponse } from './model/LoginResponse';
import { UserCredentials } from './model/UserCredentials';
import { UserSignup } from './model/UserSignUp';
@Injectable({
  providedIn: 'root'
})
export class AuthApiService {
  private baseUrl = 'http://localhost:8888/api/auth';

  constructor(private http: HttpClient) {}
  // Login API call
  authenticate(userCredentials: UserCredentials): Observable<LoginResponse> {
    return this.http.post<LoginResponse>(`${this.baseUrl}/login`, userCredentials);
  }
  register(user: UserSignup): Observable<any> {
    return this.http.post(`${this.baseUrl}/signup`, user);
  }
  saveUserSession(token: string, user: any): void {
    sessionStorage.setItem('ust.auth', token);
    sessionStorage.setItem('user', JSON.stringify(user));
    if (user && user.id) {
      sessionStorage.setItem('userId', user.id.toString());  // Store user ID separately
    }
  }

  getUserData(): any {
    const userData = sessionStorage.getItem('user');
    // Check if userData exists before parsing
    if (userData) {
      return JSON.parse(userData);
    } else {
      return null; // Return null if no user data is found
    }
  }
  // Get only the user ID from session storage
  getUserId(): string | null {
    return sessionStorage.getItem('userId');
  }
  logout(): void {
    sessionStorage.removeItem('ust.auth');
    sessionStorage.removeItem('user');
    sessionStorage.removeItem('userId')
  }

}
 
