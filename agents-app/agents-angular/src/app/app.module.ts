import { BrowserModule } from '@angular/platform-browser';
import { NgModule } from '@angular/core';
import { HttpClientModule  } from '@angular/common/http';

import { AppComponent } from './app.component';
import { HeaderComponent } from './header/header.component';
import { LoginService } from './services/login.service';
import { UnitService } from './services/unit.service';

import { AppRoutingModule } from './/app-routing.module';
import { FormsModule } from '@angular/forms';
import { LoginComponent } from './login/login.component';
import { LogoutComponent } from './logout/logout.component';
import { RegisterComponent } from './register/register.component';
import { WorkspaceComponent } from './workspace/workspace.component';
import { UnitComponent } from './unit/unit.component';
import { MessagesComponent } from './messages/messages.component';
import { ReservationsComponent } from './reservations/reservations.component';

@NgModule({
  declarations: [
    AppComponent,
    HeaderComponent,
    LoginComponent,
    LogoutComponent,
    RegisterComponent,
    WorkspaceComponent,
    UnitComponent,
    MessagesComponent,
    ReservationsComponent
  ],
  imports: [
    BrowserModule,
    HttpClientModule,
    AppRoutingModule,
    FormsModule
  ],
  providers: [LoginService, UnitService],
  bootstrap: [AppComponent]
})
export class AppModule { }
