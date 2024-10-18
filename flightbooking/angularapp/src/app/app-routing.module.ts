import { NgModule } from '@angular/core';
import { Routes, RouterModule } from '@angular/router';
import { LoginComponent } from './components/login/login.component';
import { HomeComponent } from './components/home/home.component';
import { RegisterComponent } from './components/register/register.component';
import { AddFlightComponent } from './components/add-flight/add-flight.component';
import { FlightListComponent } from './components/flight-list/flight-list.component';

const routes: Routes = [
  {path: '', component: HomeComponent},
  {path: 'login', component: LoginComponent},
  
  {path: 'signup', component: RegisterComponent},
  {path: 'add-flight', component: AddFlightComponent},
  {path: 'flight-list', component: FlightListComponent}
];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})

export class AppRoutingModule { }
