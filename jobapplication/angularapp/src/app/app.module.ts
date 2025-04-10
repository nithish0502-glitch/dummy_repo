import { BrowserModule } from '@angular/platform-browser';
import { NgModule } from '@angular/core';

import { AppRoutingModule } from './app-routing.module';
import { AppComponent } from './app.component';
import { LoginComponent } from './components/login/login.component';
import { RegisterComponent } from './components/register/register.component';
import { FormsModule, ReactiveFormsModule } from '@angular/forms';
import { HttpClientModule } from '@angular/common/http';
import { RouterModule } from '@angular/router';
import { AddJobComponent } from './components/add-job/add-job.component';
import { JobListComponent } from './components/job-list/job-list.component';
import { JobApplicationComponent } from './components/job-application/job-application.component';
import { NavbarComponent } from './components/navbar/navbar.component';
import { ViewApplicationComponent } from './components/view-application/view-application.component';
import { ApplicationDetailsComponent } from './components/application-details/application-details.component'; 
import { MyApplicationComponent } from './components/my-application/my-application.component';
import { HomeComponent } from './components/home/home.component';
 
@NgModule({
  declarations: [
    AppComponent,
    LoginComponent,
    RegisterComponent,
    AddJobComponent,
    JobListComponent,
    JobApplicationComponent,
    NavbarComponent,
    MyApplicationComponent,
    ViewApplicationComponent,
    ApplicationDetailsComponent,
    HomeComponent
  ],   
  imports: [
    BrowserModule,
    AppRoutingModule,
    FormsModule,
    HttpClientModule,
    ReactiveFormsModule,
    RouterModule
  ],
  providers: [],
  bootstrap: [AppComponent]
})
export class AppModule { }
