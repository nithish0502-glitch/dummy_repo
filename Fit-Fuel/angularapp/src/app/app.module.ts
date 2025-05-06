import { BrowserModule } from '@angular/platform-browser';
import { NgModule } from '@angular/core';
import { ReactiveFormsModule } from '@angular/forms';
import { HttpClientModule } from '@angular/common/http';
import { RouterModule } from '@angular/router';
import { AppRoutingModule } from './app-routing.module';
import { AppComponent } from './app.component';
import { AddFitnessPlanComponent } from './add-fitnessplan/add-fitnessplan.component';
import { FitnessListComponent } from './fitnessplan-list/fitnessplan-list.component';

@NgModule({
  declarations: [
    AppComponent,
    AddFitnessPlanComponent,
    FitnessListComponent,
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
