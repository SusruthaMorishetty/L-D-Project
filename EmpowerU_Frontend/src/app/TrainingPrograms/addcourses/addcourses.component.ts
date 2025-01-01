import { CommonModule } from '@angular/common';
import { Component } from '@angular/core';
import { FormBuilder, FormGroup, ReactiveFormsModule, Validators } from '@angular/forms';
import { Router, RouterModule } from '@angular/router';
import { CourseService } from '../../course.service';

@Component({
  selector: 'app-addcourses',
  standalone: true,
  imports: [ReactiveFormsModule,CommonModule,RouterModule],
  templateUrl: './addcourses.component.html',
  styleUrl: './addcourses.component.css'
})
export class AddcoursesComponent {
  courseForm:FormGroup;

  constructor(private fb:FormBuilder,private courseService:CourseService,private router:Router){
    this.courseForm = this.fb.group({
      title: ['', [Validators.required, Validators.minLength(5)]],
      description: ['', [Validators.required, Validators.minLength(10)]],
      contentUrl: ['', [Validators.required, Validators.pattern('https?://.+')]]
    });
  }


  onSubmit(): void {
    if (this.courseForm.valid) {
      const newCourse = this.courseForm.value;
      this.courseService.addCourse(newCourse).subscribe();
      console.log(newCourse);
      alert('Course Created Successfully!');
      this.courseForm.reset();
      this.router.navigate(['/courses']);
    } else {
      alert('Please fill in the required fields correctly.');
    }
  }
}
