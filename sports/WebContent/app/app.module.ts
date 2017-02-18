import { NgModule }      from '@angular/core';
import { MaterialModule } from '@angular/material';
import {APP_BASE_HREF} from '@angular/common';
import { BrowserModule } from '@angular/platform-browser';
import { FormsModule } from '@angular/forms';
import { AppComponent }  from './app.component';
import { TeamMaker }  from './teammakerpage';
import {Commonservice} from './Commonservice'
import { RegistrationComponent }  from './registrationpart.component';
import { AppRoutingModule }     from './app-routing.module';
import { TeamRegistration }  from './teamregistration';

@NgModule({
  imports:      [ BrowserModule,FormsModule,MaterialModule.forRoot(),AppRoutingModule],
  declarations: [ AppComponent ,TeamMaker,RegistrationComponent,TeamRegistration],
  bootstrap:    [ AppComponent],
   providers: [{provide: APP_BASE_HREF, useValue: '/'},Commonservice],
  entryComponents: [TeamRegistration]
})
export class AppModule { }
