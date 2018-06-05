import { BrowserModule } from '@angular/platform-browser';
import { NgModule } from '@angular/core';
import { FormsModule }   from '@angular/forms';
import { AppRoutingModule } from './app-routing.module';

import { AppComponent } from './app.component';
import { HttpClientModule } from "@angular/common/http";
import { LoginComponent } from './login/login.component';
import { RegistrationComponent } from './registration/registration.component';
import { HashLocationStrategy, LocationStrategy } from '@angular/common';
import { LoginServiceService } from './services/login-service.service';
import { HomeComponent } from './home/home.component';
import { ProfileComponent } from './profile/profile.component';
import { HomeService } from './services/home.service';


@NgModule({
  declarations: [
    AppComponent,
    LoginComponent,
    RegistrationComponent,
    HomeComponent,
    ProfileComponent
  ],
  imports: [
    BrowserModule,
    AppRoutingModule,
    HttpClientModule,
    FormsModule
  ],
  providers: [//{provide: LocationStrategy, useClass: HashLocationStrategy},
  LoginServiceService,
  HomeService],
  bootstrap: [AppComponent]
})
export class AppModule { }
