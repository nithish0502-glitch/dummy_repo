import { BrowserModule } from '@angular/platform-browser';
import { NgModule } from '@angular/core';
import { ReactiveFormsModule } from '@angular/forms';
import { HttpClientModule } from '@angular/common/http';
import { RouterModule } from '@angular/router';
import { AppRoutingModule } from './app-routing.module';
import { AppComponent } from './app.component';
import { FitnessListComponent } from './fp-list/fp-list.component';
import { AddFitnessPlanComponent } from './add-fitness-plan/add-fitness-plan.component';
import { AddFitnessplanComponent } from './add-fitnessplan/add-fitnessplan.component';
import { FitnessplanListComponent } from './fitnessplan-list/fitnessplan-list.component';

@NgModule({
  declarations: [
    AppComponent,
    fitnessplsListComponent,
    AddFpComponent,
    AddFitnessPlanComponent,
    AddFitnessplanComponent,
    FitnessplanListComponent,
  ],
  imports: [
    BrowserModule,
    AppRoutingModule,
    ReactiveFormsModule,
    RouterModule.forRoot([]),
    HttpClientModule
  ],
  providers: [],
  bootstrap: [AppComponent]
})
export class AppModule { }
