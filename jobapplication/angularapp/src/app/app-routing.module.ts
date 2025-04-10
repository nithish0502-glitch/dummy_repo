import { NgModule } from '@angular/core';
import { Routes, RouterModule } from '@angular/router';
import { LoginComponent } from './components/login/login.component';
import { RegisterComponent } from './components/register/register.component';
import { AddJobComponent } from './components/add-job/add-job.component';
import { JobListComponent } from './components/job-list/job-list.component';
import { JobApplicationComponent } from './components/job-application/job-application.component';
import { MyApplicationComponent } from './components/my-application/my-application.component';
import { ViewApplicationComponent } from './components/view-application/view-application.component';
import { ApplicationDetailsComponent } from './components/application-details/application-details.component';
import { HomeComponent } from './components/home/home.component';
  
const routes: Routes = [
  {path: 'login', component: LoginComponent},
  {path: '', component: HomeComponent},
  {path: 'signup', component: RegisterComponent},
  {path:'add-job',component:AddJobComponent},
  {path:'job-list',component:JobListComponent},
  {path: 'job-application/:jobId',component:JobApplicationComponent},
  {path:'my-application',component:MyApplicationComponent},
  {path:'view-all-application',component:ViewApplicationComponent},
  { path: 'job-details/:jobId', component: ApplicationDetailsComponent },
  { path: 'add-job/:jobId', component: AddJobComponent },
];
  
@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule { }
 