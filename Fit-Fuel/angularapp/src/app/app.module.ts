import { BrowserModule } from '@angular/platform-browser';
import { NgModule } from '@angular/core';
import { ReactiveFormsModule } from '@angular/forms';
import { HttpClientModule } from '@angular/common/http';

import { AppRoutingModule } from './app-routing.module';
import { AppComponent } from './app.component';
import { FitnessListComponent } from './fp-list/fp-list.component';
import { AddFitnessPlanComponent } from './add-fp/add-fp.component';

@NgModule({
  declarations: [
    AppComponent,
    FitnessListComponent,
    AddFitnessPlanComponent
  ],
  imports: [
    BrowserModule,
    AppRoutingModule,
    ReactiveFormsModule,
    HttpClientModule
  ],
  providers: [],
  bootstrap: [AppComponent]
})
export class AppModule { }
