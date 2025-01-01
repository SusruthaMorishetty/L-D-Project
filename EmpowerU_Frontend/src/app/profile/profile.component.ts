import { CommonModule } from '@angular/common';
import { HttpErrorResponse } from '@angular/common/http';
import { Component } from '@angular/core';
import { FormsModule } from '@angular/forms';
import { UserDetails } from '../model/UserDetails';
import { UserService } from '../user.service';

@Component({
  selector: 'app-profile',
  standalone: true,
  imports: [CommonModule,FormsModule],
  templateUrl: './profile.component.html',
  styleUrl: './profile.component.css'
})
export class ProfileComponent {
  userDetails: UserDetails | null = null;
  errorMessage: string = '';

  constructor(private userService: UserService) {}

  ngOnInit(): void {
    // Fetch userId from sessionStorage
    const userId = sessionStorage.getItem('userId');
    if (!userId) {
      this.errorMessage = 'User ID is not available in session storage.';
      return;
    }

    // Convert userId to a number and fetch user details
    this.userService.getUserDetails(Number(userId)).subscribe({
      next: (userDetails) => {
        console.log('Fetched user details:', userDetails);
        this.userDetails = userDetails;
      },
      error: (error: HttpErrorResponse) => {
        console.error('Error fetching user details:', error);
        this.errorMessage = 'Could not fetch user details. Please try again later.';
      }
    });
  }

}
