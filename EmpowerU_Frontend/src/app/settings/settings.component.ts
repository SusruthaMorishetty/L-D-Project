import { CommonModule } from '@angular/common';
import { Component } from '@angular/core';
import { FormsModule } from '@angular/forms';

@Component({
  selector: 'app-settings',
  standalone: true,
  imports: [CommonModule,FormsModule],
  templateUrl: './settings.component.html',
  styleUrl: './settings.component.css'
})
export class SettingsComponent {

  settings = {
    username: '',
    email: '',
    role: 'user'
  };

  saveSettings(): void {
    console.log('Settings saved:', this.settings);
    // You can implement API calls here to save the settings
  }
 
}
