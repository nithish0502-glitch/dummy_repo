import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import { CricketPlayerListComponent } from './cricketplayer-list/cricketplayer-list.component';
import { AddCricketPlayerComponent } from './add-cricketplayer/add-cricketplayer.component';

const routes: Routes = [
  { path: '', redirectTo: '/players', pathMatch: 'full' },
  { path: 'players', component: CricketPlayerListComponent },
  { path: 'add-player', component: AddCricketPlayerComponent }
];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule { }


