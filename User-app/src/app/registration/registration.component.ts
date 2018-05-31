import { Component, OnInit } from '@angular/core';
import { NgForm } from '@angular/forms';
import { LoginServiceService } from '../services/login-service.service';
import { Router } from '@angular/router';

@Component({
  selector: 'app-registration',
  templateUrl: './registration.component.html',
  styleUrls: ['/registration.component.css']
  
})
export class RegistrationComponent implements OnInit {

  constructor(private loginService: LoginServiceService, 
  private router: Router) { }

  ngOnInit() {
 
  }

  onSubmit(form : NgForm) {

   this.loginService.submitRegistration(form.value).subscribe();
   this.router.navigateByUrl('login');

  }

}
