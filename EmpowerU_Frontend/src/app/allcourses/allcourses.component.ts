import { CommonModule } from '@angular/common';
import { Component } from '@angular/core';
import { ActivatedRoute, RouterModule } from '@angular/router';
import { AllcoursesService, Course } from '../allcourses.service';

@Component({
  selector: 'app-allcourses',
  standalone: true,
  imports: [CommonModule,RouterModule],
  templateUrl: './allcourses.component.html',
  styleUrl: './allcourses.component.css'
})
export class AllcoursesComponent {

  courses:Course[]=[];
  loading: boolean = false;

  constructor(private allcourseService:AllcoursesService, private route: ActivatedRoute){}

  ngOnInit(): void {
    // Fetch all courses when the component is initialized
    this.fetchAllCourses();
  }

  // Fetch all courses from the service
  fetchAllCourses(): void {
    this.loading = true;
    this.allcourseService.getAllCourses().subscribe(
      (data) => {
        this.courses = data;  // Assign the received courses to the component's courses array
        this.loading = false;  // Hide loading spinner
      },
      (error) => {
        console.error('Error fetching courses:', error);
        this.loading = false;  // Hide loading spinner on error
      }
    );
  }
}
