import { CommonModule } from '@angular/common';
import { Component, OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';

@Component({
  selector: 'app-search',
  standalone: true,
  imports: [CommonModule],
  templateUrl: './search.component.html',
  styleUrl: './search.component.css'
})
export class SearchComponent implements OnInit {
  query: string = '';
  results: any[] = []; // Replace `any[]` with your actual result model

  constructor(private route:ActivatedRoute){}

  ngOnInit() {
     // Retrieve the query parameter from the URL
    this.route.queryParams.subscribe(params => {
      this.query = params['q'] || '';
      if (this.query) {
        this.performSearch(this.query);
      }
    });
  }

  performSearch(query: string) {
    // Replace this with your actual search API call
    console.log(`Searching for: ${query}`);
    this.results = [
      { name: 'Result 1', description: 'Description of result 1' },
      { name: 'Result 2', description: 'Description of result 2' }
    ];
  }

}
