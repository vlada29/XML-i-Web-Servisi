import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';
import { LoginComponent } from './login/login.component';
import { RegisterComponent } from './register/register.component';
import { Routes, RouterModule } from '@angular/router';
//import { UserprofileComponent } from './userprofile/userprofile.component';
import { LogoutComponent } from './logout/logout.component';
import {WorkspaceComponent} from './workspace/workspace.component';
//import { AuthGuard} from './auth.guard';

const routes: Routes = [
  {
    path: '',
    component: WorkspaceComponent,
    pathMatch: 'full'
  },
  {
    path: 'login',
    component: LoginComponent
    },
    {
      path: 'register',
      component: RegisterComponent
    },
 {
   path: 'logout',
   component: LogoutComponent
}
];
@NgModule({
  imports: [
    CommonModule,
    RouterModule.forRoot(routes)
  ],
  exports: [RouterModule],
  declarations: []
})
export class AppRoutingModule { }
