import { CommonModule } from '@angular/common';
import { Component, OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';
import { Course, CourseService } from '../../course.service';

@Component({
  selector: 'app-courses',
  standalone: true,
  imports: [CommonModule],
  templateUrl: './courses.component.html',
  styleUrl: './courses.component.css'
})
export class CoursesComponent implements OnInit{
  courses:Course[]=[];
  
  courseImages: string[] = [];
 

  constructor(private courseService:CourseService, private route: ActivatedRoute){}
  private trainingProgramId = Number(localStorage.getItem('trainingProgramId'));

  ngOnInit(): void {
    // this.trainingProgramId = +this.route.snapshot.paramMap.get('trainingProgramId')!;
    
    // Fetch courses for this specific trainingProgramId
    this.getCoursesByTrainingProgramId(this.trainingProgramId);
  }

  getCoursesByTrainingProgramId(trainingProgramId: number): void {
    this.courseService.getCoursesByTrainingProgram(trainingProgramId).subscribe(courses => {
      this.courses = courses;
      this.assignRandomImages();
    });
  }


  assignRandomImages(): void {
    if (!this.courses || this.courses.length === 0) return; // Avoid errors if courses are empty
    const availableImages = [
      'assets/cardImages/c1.jpg',
    'assets/cardImages/c2.jpg',
    'assets/cardImages/c3.jpg',
    'assets/cardImages/c4.jpg',
    'assets/cardImages/c5.jpg',
    'assets/cardImages/c6.jpg',
    'assets/cardImages/c7.jpg',
    'assets/cardImages/c8.jpg',
    'assets/cardImages/c9.jpg',
    'assets/cardImages/c10.jpg'
    ];
    this.courseImages = this.courses.map(
      () => availableImages[Math.floor(Math.random() * availableImages.length)]
    );
  }
  


   // Delete course by passing course ID
   deleteCourse(courseId: number): void {
    this.courseService.deleteCourse(courseId)
      .subscribe({
        next: () => {
          // Course will be automatically removed from this.courses 
          // due to the BehaviorSubject in the service
         
        },
        error: (err) => {
          
          console.error('Error deleting course', err);
        }
      });
  }
}
