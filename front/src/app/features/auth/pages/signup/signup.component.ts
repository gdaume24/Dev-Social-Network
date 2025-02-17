import { Component, inject } from '@angular/core';
import { FormControl, FormGroup, ReactiveFormsModule } from '@angular/forms';
import { MatIconModule } from '@angular/material/icon';
import { AuthService } from '../../services/auth.service';
import { Router } from '@angular/router';
import { BanniereComponent } from '../../components/banniere/banniere.component';

@Component({
  selector: 'app-signup',
  imports: [MatIconModule, ReactiveFormsModule, BanniereComponent],
  templateUrl: './signup.component.html',
  styleUrl: './signup.component.css',
})
export class SignupComponent {
  authService = inject(AuthService);
  applyForm = new FormGroup({
    username: new FormControl(''),
    email: new FormControl(''),
    password: new FormControl(''),
  });
  constructor(private router: Router) {}

  registration() {
    this.authService.registration(
      this.applyForm.value.username ?? '',
      this.applyForm.value.email ?? '',
      this.applyForm.value.password ?? ''
    );
    this.router.navigate(['/themes']);
  }
  navigateBack() {
    this.router.navigate(['/']);
  }
}
