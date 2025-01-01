import { CommonModule } from '@angular/common';
import { Component } from '@angular/core';
import { ActivatedRoute, Router, RouterModule } from '@angular/router';
import { TraineesService, User } from '../trainees.service';


@Component({
  selector: 'app-trainees',
  standalone: true,
  imports: [CommonModule,RouterModule],
  templateUrl: './trainees.component.html',
  styleUrl: './trainees.component.css'
})
export class TraineesComponent {
  users: User[] = [];
  loading: boolean = false;
  error: string = '';

  programId!: number ;


  constructor(private traineeService: TraineesService,private route:ActivatedRoute) { }

  ngOnInit(): void {
    // Fetch users when the component initializes
    this.route.queryParams.subscribe(params => {
      this.programId = params['programId']; // Get 'programId' from query parameters
      console.log('Program ID:', this.programId); // You can use this programId for further logic
    });
    this.fetchUsers();
  }

  // Method to fetch users
  fetchUsers(): void {
    this.loading = true;
    this.traineeService.getAllUsers().subscribe(
      (response) => {
        this.users = response.filter(user => 
          user.role === 'user' && user.name != null && user.name.trim() !== ''
        ); // Store the list of users
        this.loading = false; // Hide loading
      },
      (error) => {
        console.error('Error fetching users:', error);
        this.error = 'Failed to load users'; // Set error message
        this.loading = false; // Hide loading
      }
    );
  }
  

 

  addUserToProgram(userId: number): void {
    // Replace with actual programId, maybe dynamically selected
    this.traineeService.addUserToProgram(userId, this.programId).subscribe(
      (response) => {
        alert(`User with ID: ${userId} added to program ${this.programId}`);
        // Optionally refresh the list or handle response as needed
      },
      (error) => {
        console.error('Error adding user to program:', error);
        alert('User is already Enrolled in TrainingProgram');
      }
    );
  }

  // Method to remove user from a program
  removeUserFromProgram(userId: number): void {
    // Replace with the actual programId
    if (confirm('Are you sure you want to remove this user from the program?')) {
      this.traineeService.removeUserFromProgram(userId, this.programId).subscribe(
        () => {
          alert(`User with ID: ${userId} removed from program ${this.programId}`);
          // Optionally refresh the list of users or show success message
        },
        (error) => {
          console.error('Error removing user from program:', error);
          alert('User is not Enrolled in TrainingProgram');
        }
      );
    }
  }
  
}
