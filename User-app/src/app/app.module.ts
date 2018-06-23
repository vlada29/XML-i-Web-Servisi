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
import { ProfileService } from './services/profile.service';
import { ReservationComponent } from './reservation/reservation.component';
import { InboxComponent } from './inbox/inbox.component';
import { MessageComponent } from './message/message.component';
import { SentComponent } from './sent/sent.component';
import { RatingService } from './services/rating.service';


@NgModule({
  declarations: [
    AppComponent,
    LoginComponent,
    RegistrationComponent,
    HomeComponent,
    ProfileComponent,
    ReservationComponent,
    InboxComponent,
    MessageComponent,
    SentComponent
  ],
  imports: [
    BrowserModule,
    AppRoutingModule,
    HttpClientModule,
    FormsModule
  ],
  providers: [{provide: LocationStrategy, useClass: HashLocationStrategy},
  LoginServiceService,
  HomeService,
  ProfileService, 
  RatingService],
  bootstrap: [AppComponent]
})
export class AppModule { }
