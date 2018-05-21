import { BrowserModule } from '@angular/platform-browser';
import { NgModule } from '@angular/core';

import { AppRoutingModule } from './app-routing.module';
import { HttpClientModule } from '@angular/common/http';

import { AppComponent } from './app.component';
import { LoginComponent } from './login/login.component';
import { CatalogEditingComponent } from './catalog-editing/catalog-editing.component';
import { CommentEditingComponent } from './comment-editing/comment-editing.component';
import { UserEditingComponent } from './user-editing/user-editing.component';
import { AgentEditingComponent } from './agent-editing/agent-editing.component';
import { WorkspaceComponentComponent } from './workspace-component/workspace-component.component';
import { UserServiceService } from './services/user-service.service';
import { CommentServiceService } from './services/comment-service.service';
import { AgentServiceService } from './services/agent-service.service';
import { CatalogServiceService } from './services/catalog-service.service';
import { LoginServiceService } from './services/login-service.service';


@NgModule({
  declarations: [
    AppComponent,
    LoginComponent,
    CatalogEditingComponent,
    CommentEditingComponent,
    UserEditingComponent,
    AgentEditingComponent,
    WorkspaceComponentComponent
  ],
  imports: [
    BrowserModule,
    AppRoutingModule,
    HttpClientModule
  ],
  providers: [ UserServiceService, CommentServiceService, AgentServiceService, CatalogServiceService, LoginServiceService ],
  bootstrap: [AppComponent]
})
export class AppModule { }
