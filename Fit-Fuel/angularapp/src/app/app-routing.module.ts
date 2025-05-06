import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import { FitnessListComponent } from './fitnessplan-list/fitnessplan-list.component';
import { AddFitnessPlanComponent } from './add-fitnessplan/add-fitnessplan.component';

const routes: Routes = [
  { path: '', redirectTo: '/plans', pathMatch: 'full' },
  { path: 'plans', component: FitnessListComponent },
  { path: 'add-plan', component: AddFitnessPlanComponent }
];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule { }

