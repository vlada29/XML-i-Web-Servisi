import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';
import { LoginComponent } from './login/login.component';
import { RegisterComponent } from './register/register.component';
import { Routes, RouterModule } from '@angular/router';
//import { UserprofileComponent } from './userprofile/userprofile.component';
import { LogoutComponent } from './logout/logout.component';
import {WorkspaceComponent} from './workspace/workspace.component';
import {SyncComponent} from './sync/sync.component';
//import { AuthGuard} from './auth.guard';

const routes: Routes = [
  {
    path: '',
    component: LoginComponent,
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
},
{
  path: 'workspace',
  component: WorkspaceComponent
},
{
  path: 'sync',
  component: SyncComponent
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
