import { CommonModule } from '@angular/common';
import { HttpClientModule, HttpErrorResponse } from '@angular/common/http';
import { Component } from '@angular/core';
import { FormsModule } from '@angular/forms';
import { Router } from '@angular/router';
import { AuthApiService } from '../../auth-api.service';
import { UserCredentials } from '../../model/UserCredentials';
import { UserService } from '../../user.service';

@Component({
  selector: 'app-login',
  standalone: true,
  imports: [FormsModule,HttpClientModule,CommonModule],
  templateUrl: './login.component.html',
  styleUrl: './login.component.css'
})
export class LoginComponent {
  userCredentials: UserCredentials = { username: '', password: '' };
  errorMessage: string = '';

  constructor(private authApiService: AuthApiService, private router: Router,private userService: UserService) {}

  // signin(): void {
  //   this.authApiService.authenticate(this.userCredentials).subscribe(
  //     (response) => {
  //       sessionStorage.setItem('ust.auth', response.jwt);
  //       this.router.navigate(['/dashboard']);
  //     },
  //     (error: HttpErrorResponse) => {
  //       this.errorMessage = 'Invalid username or password.';
  //       console.error('Login error:', error);
  //     }
  //   );
  // }


  signin(): void {
    this.authApiService.authenticate(this.userCredentials).subscribe(
      (response) => {
        sessionStorage.setItem('ust.auth', response.jwt);
        sessionStorage.setItem('userId',response.userId);

        console.log(response.userId);
        
        // Ensure that user data is correctly saved as well
        const user = { username: this.userCredentials.username }; // Or from the API response
        sessionStorage.setItem('user', JSON.stringify(user));
        const userId: number = Number(response.userId);
        this.userService.getUserDetails(userId).subscribe(
            (userDetails) => {
            //   sessionStorage.setItem('userName', userDetails.name);
            //   sessionStorage.setItem('userRole', userDetails.role);
            console.log(userDetails.role);
    
              // Redirect based on user role
              if (userDetails.role === 'admin') {
                this.router.navigate(['/home']);
              } else if (userDetails.role === 'user') {
                this.router.navigate(['/home']);
              } else {
                this.router.navigate(['/home']);
              }
            },
            (error: HttpErrorResponse) => {
              console.error('Error fetching user details:', error);
              this.errorMessage = 'Could not fetch user details.';
            }
          );

      },
      (error: HttpErrorResponse) => {
        this.errorMessage = 'Invalid username or password.';
        console.error('Login error:', error);
      }
    );
  }

  clearErrorMessage(): void {
    this.errorMessage = '';
  }
}




