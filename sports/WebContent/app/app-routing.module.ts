import { NgModule }             from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import { AppComponent }  from './app.component';
import { RegistrationComponent }  from './registrationpart.component';

import { TeamMaker }  from './teammakerpage';
const routes: Routes = [
 
  { path: 'registration',  component: RegistrationComponent },
  { path: 'teampage', component: TeamMaker }
];
@NgModule({
  imports: [ RouterModule.forRoot(routes) ],
  exports: [ RouterModule ]
})
export class AppRoutingModule {}