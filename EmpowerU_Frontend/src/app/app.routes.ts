import { Routes } from '@angular/router';
import { DashboardComponent } from './CoreComponents/Dashboards/dashboard/dashboard.component';
import { FeedbackComponent } from './feedback/feedback.component';
import { LoginComponent } from './CoreComponents/login/login.component';
import { MainComponent } from './CoreComponents/main/main.component';
import { ProfileComponent } from './profile/profile.component';
import { ProgressComponent } from './progress/progress.component';
import { SearchComponent } from './CoreComponents/search/search.component';
import { SignupComponent } from './CoreComponents/signup/signup.component';
import { TrainingProgramsComponent } from './TrainingPrograms/training-programs/training-programs.component';
import { AddTrainingProgramsComponent } from './TrainingPrograms/add-training-programs/add-training-programs.component';
import { CoursesComponent } from './TrainingPrograms/courses/courses.component';
import { AddcoursesComponent } from './TrainingPrograms/addcourses/addcourses.component';
import { UserDashboardComponent } from './UserComponents/user-dashboard/user-dashboard.component';
import { UserTrainingProgramsComponent } from './UserComponents/user-training-programs/user-training-programs.component';
import { UserCoursesComponent } from './UserComponents/user-courses/user-courses.component';
import { UserFeedbackComponent } from './UserComponents/user-feedback/user-feedback.component';
//import { TrackProgressComponent } from './track-progress/track-progress.component';
import { TraineesComponent } from './trainees/trainees.component';
import { AllcoursesComponent } from './allcourses/allcourses.component';
import { SettingsComponent } from './settings/settings.component';



export const routes: Routes = [
  {path:'',component:MainComponent,pathMatch:'full'},
  {path:'home',component:MainComponent},
  {path:'search',component:SearchComponent},
  { path: 'loginpage', component: LoginComponent },
  {path:'profile',component:ProfileComponent},
  {path:'dashboard',component:DashboardComponent},
  {path:'trainees',component:TraineesComponent},
  {path:'progress',component:ProgressComponent},
  {path:'feedback',component:FeedbackComponent},
  {path:'courses',component:AllcoursesComponent},
  {path:'user-feedback',component:UserFeedbackComponent},
  { path: 'courses/:trainingProgramId', component: CoursesComponent },
  { path: 'usercourses/:trainingProgramId', component: UserCoursesComponent },
  {path:'add-course',component:AddcoursesComponent},
  // {path:'trackprogress', component:TrackProgressComponent},
  {path:'feedback',component:FeedbackComponent},
  {path:'settings',component:SettingsComponent},
  {path:'signup',component:SignupComponent},
  {path:'userdashboard',component:UserDashboardComponent},
  {path:'usertrainingprograms',component:UserTrainingProgramsComponent},
  {path:'training-programs',component:TrainingProgramsComponent},
  {path:'add-training-program',component:AddTrainingProgramsComponent},
  { path: '**', redirectTo: '/home' },
 
];
