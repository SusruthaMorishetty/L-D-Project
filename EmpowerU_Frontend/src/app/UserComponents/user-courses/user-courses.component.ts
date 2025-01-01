import { CommonModule } from '@angular/common';
import { Component, OnInit } from '@angular/core';
import { ActivatedRoute, Router, RouterModule } from '@angular/router';
import { Course, UserCourseService } from '../../user-course.service';
@Component({
  selector: 'app-user-courses',
  standalone: true,
  imports: [RouterModule,CommonModule],
  templateUrl: './user-courses.component.html',
  styleUrl: './user-courses.component.css'
})
export class UserCoursesComponent implements OnInit {
  courses:Course[]=[];
  constructor(private usercourseService:UserCourseService, private route: ActivatedRoute,private router: Router){}
  private trainingProgramId = Number(localStorage.getItem('trainingProgramId'));
  ngOnInit(): void {
    // this.trainingProgramId = +this.route.snapshot.paramMap.get('trainingProgramId')!;
    // Fetch courses for this specific trainingProgramId
    this.getCoursesByTrainingProgramId(this.trainingProgramId);
  }
  getCoursesByTrainingProgramId(trainingProgramId: number): void {
    this.usercourseService.getCoursesByTrainingProgram(trainingProgramId).subscribe(courses => {
      this.courses = courses;
    });
  }
  saveIdAndNavigate(courseId: number): void {
    // Store the trainingProgramId in localStorage
    localStorage.setItem('courseId', courseId.toString());
    this.router.navigate(['/user-feedback']);
  }

}
 
