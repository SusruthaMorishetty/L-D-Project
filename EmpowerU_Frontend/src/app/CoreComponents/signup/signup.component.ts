import { CommonModule } from '@angular/common';
import { Component } from '@angular/core';
import { FormsModule } from '@angular/forms';
import { Router, RouterModule } from '@angular/router';
import { AuthApiService } from '../../auth-api.service';
import { UserSignup } from '../../model/UserSignUp';

@Component({
  selector: 'app-signup',
  standalone: true,
  imports: [FormsModule,RouterModule,CommonModule],
  templateUrl: './signup.component.html',
  styleUrl: './signup.component.css'
})
export class SignupComponent {
  user: UserSignup = {
    username: '',
    password: '',
    email: '',
    role: 'user',
  };

  errorMessage: string = '';

  constructor(private authApiService: AuthApiService, private router: Router) {}

  signup(): void {
    this.authApiService.register(this.user).subscribe(
      () => {
        alert('Signup successful!');
        this.router.navigate(['/loginpage']); // Redirect to login page
      },
      (error) => {
        console.error('Error during signup:', error);
        this.errorMessage = error.error.message || 'An error occurred. Please try again.';
      }
    );
  }

}
