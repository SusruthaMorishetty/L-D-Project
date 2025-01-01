import { CommonModule } from '@angular/common';
import { AfterViewInit, Component, OnInit } from '@angular/core';
import { RouterModule } from '@angular/router';
import { FeedbackService } from '../../feedback.service';
import { NavbarComponent } from '../../Navbars/navbar/navbar.component';

@Component({
  selector: 'app-main',
  standalone: true,
  imports: [NavbarComponent , RouterModule, CommonModule],
  templateUrl: './main.component.html',
  styleUrl: './main.component.css'
})
export class MainComponent implements AfterViewInit, OnInit{
  feedbacks: any[]=[];

  constructor(private feedbackService: FeedbackService){

  }

  ngOnInit(): void {
    this.fetchFeedbacks();
  }
  ngAfterViewInit(): void {
    const carouselElement = document.getElementById('educationalCarousel');
    if (carouselElement) {
      const bootstrap = (window as any).bootstrap; 
      const carousel = new bootstrap.Carousel(carouselElement, {
        interval: 3000, // Auto-slide interval in milliseconds
        ride: 'carousel', // Ensures it starts automatically
      });
    }
  }

  fetchFeedbacks(): void {
    this.feedbackService.fetchAllFeedbacks().subscribe(
      (data) => {
        this.feedbacks = data;
      },
      (error) => {
        console.error('Error fetching feedbacks', error);
      }
    );
  }

}
