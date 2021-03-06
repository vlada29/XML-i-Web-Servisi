import { Component, OnInit } from '@angular/core';
import { NgForm } from '@angular/forms';
import { LoginServiceService } from '../services/login-service.service';
import { Router } from '@angular/router';

@Component({
  selector: 'app-login',
  templateUrl: './login.component.html',
  styleUrls: ['./login.component.css']
})
export class LoginComponent implements OnInit {

  constructor(private loginService: LoginServiceService,
  private router: Router) { }

  private loggedUser;

  ngOnInit() {
  }

  onSubmit(form : NgForm) {

    this.loginService.submitLogin(form.value).subscribe(
    data => { 
      this.loggedUser = data;
      localStorage.setItem('currentUserId', this.loggedUser.hjid);
      console.log(localStorage.getItem('currentUserId'));
      this.router.navigate(['/home']);
    }
      
    );
    
    
 
   }

}
