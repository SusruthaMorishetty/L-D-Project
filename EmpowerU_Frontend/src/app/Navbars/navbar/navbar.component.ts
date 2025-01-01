import { CommonModule } from '@angular/common';
import { Component, OnInit } from '@angular/core';
import { FormsModule } from '@angular/forms';
import { NavigationEnd, Router, RouterModule } from '@angular/router';
import { AuthApiService } from '../../auth-api.service';
import { LoginComponent } from '../../CoreComponents/login/login.component';
import { SignupComponent } from '../../CoreComponents/signup/signup.component';
import { UserDetails } from '../../model/UserDetails';
import { UserService } from '../../user.service';
@Component({
  selector: 'app-navbar',
  standalone: true,
  imports: [CommonModule,FormsModule, RouterModule, LoginComponent, SignupComponent],
  templateUrl: './navbar.component.html',
  styleUrl: './navbar.component.css'
})
export class NavbarComponent implements OnInit {
  isLoggedIn: boolean = false;
  username: string = '';
  dropdownVisible: boolean = false;
  searchQuery: string = '';
  navLinks: { label: string; link: string }[] = [];
  currentRoute: string = '';
  userRole:string='';
  navConfig: { [key: string]: { label: string; link: string }[] } = {
    home: [
      { label: 'Login', link: '/loginpage' },
      { label: 'Signup', link: '/signup' }
    ],
    trainingPrograms: [
      { label: 'Add Training Program', link: '/add-training-program' },
    ],
    // dashboard: [
    //   { label: 'Training Programs', link: '/training-programs' },
    // ],
    courses: [
      { label: 'Add Course', link: '/add-course' },
      { label: 'Training Programs', link: '/training-programs' },
    ],
    userdashboard: [
      {label: 'View Training Programs', link:'/usertrainingprograms'},
    ],
    trackprogress: [
      {label:'View Progress',link:'/progress'}
    ],
    addtrainingprograms:[
      {label:'Training Programs',link:'/training-programs'}
    ],
    addcourse:[
      {label:'Courses',link:'/courses/:trainingProgramId'},
      {label:'Training Programs',link:'/training-programs'}
    ]
  };
  constructor(private router: Router, private authApiService: AuthApiService,private userService:UserService) {}
  ngOnInit(): void {
    this.router.events.subscribe((event) => {
      if (event instanceof NavigationEnd) {
        this.currentRoute = this.getRouteKey(event.urlAfterRedirects);
        this.navLinks = this.navConfig[this.currentRoute] || this.navConfig['home'];
        this.checkLoginStatus();
      }
    });
    this.checkLoginStatus();
  }
  fetchUserRole(): void {
    const userId = this.authApiService.getUserId(); // Get user ID
    if (userId) {
      this.userService.getUserDetails(Number(userId)).subscribe(
        (user: UserDetails) => {
          this.userRole = user.role || ''; // Set user role
          console.log('User role:', this.userRole); // Debugging

          // Avoid duplicates by checking if the link already exists
          if (this.userRole === 'admin' && !this.navLinks.some(link => link.link === '/dashboard')) {
            this.navLinks.push({ label: 'Admin Dashboard', link: '/dashboard' });
          } else if (this.userRole === 'user' && !this.navLinks.some(link => link.link === '/userdashboard')) {
            this.navLinks.push({ label: 'User Dashboard', link: '/userdashboard' });
          }
        },
        (error) => {
          console.error('Error fetching user details:', error);
        }
      );
    } else {
      console.error('No user ID found in session storage.');
    }
  }


  checkLoginStatus(): void {
    const userData = this.authApiService.getUserData();
    if (userData) {
      this.isLoggedIn = true;
      this.username = userData.username || '';
      this.navLinks = this.navConfig[this.currentRoute] || this.navConfig['home']; // Set default links
      this.fetchUserRole(); // Fetch and set user role, updating navLinks
    } else {
      this.isLoggedIn = false;
      this.navLinks = this.navConfig['home']; // Default links for non-logged-in users
    }
  }


  closeDropdown():void{
    this.dropdownVisible =false;
  }
  toggleDropdown(): void {
    this.dropdownVisible = !this.dropdownVisible;
  }
  // logout(): void {
  //   this.authApiService.logout();
  //   this.isLoggedIn = false;
  //   this.dropdownVisible = false;
  //   this.navLinks = this.navConfig['home'];
  //   console.log('NavLinks after logout:', this.navLinks);
  //   this.router.navigate(['/home']);
  // }

  logout(): void {
    this.authApiService.logout(); // Perform logout operation
    this.isLoggedIn = false;
    this.username = '';
    this.userRole = ''; // Clear the user role
    this.dropdownVisible = false;
    this.navLinks = [...this.navConfig['home']]; // Reset to default home navLinks (create a new array)
    console.log('NavLinks after logout:', this.navLinks); // Debugging line
    this.router.navigate(['/home']); // Redirect to the home page
  }

  // Map URL to a route key for the navConfig
  private getRouteKey(url: string): string {
    if (url.includes('/training-programs')) {
      return 'trainingPrograms';
    } else if (url.includes('/dashboard')) {
      return 'dashboard';
    } else if (url.includes('/courses')) {
      return 'courses';
    }else if(url.includes('/userdashboard')){
      return 'userdashboard';
    }else if(url.includes('/trackprogress')){
      return 'trackprogress';
    }else if(url.includes('/add-training-program')){
      return 'addtrainingprograms';
    } else if(url.includes('/add-course')){
      return 'addcourse';
    } else {
      return 'home';
    }
  }
  onSearch(): void {
    if (this.searchQuery.trim()) {
      this.router.navigate(['/search'], { queryParams: { q: this.searchQuery } });
    } else {
      alert('Please enter a search term.');
    }
  }
}
