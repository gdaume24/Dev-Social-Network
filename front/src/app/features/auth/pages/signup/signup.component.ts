import { Component, inject } from '@angular/core';
import {
  FormBuilder,
  FormControl,
  FormGroup,
  ReactiveFormsModule,
  Validators,
} from '@angular/forms';
import { MatIconModule } from '@angular/material/icon';
import { AuthService } from '../../services/auth.service';
import { Router } from '@angular/router';
import { BanniereComponent } from '../../components/banniere/banniere.component';
import { ApiService } from '../../../../service/api.service';
import { RegisterRequest } from '../../interfaces/RegisterRequest.interface';
import { AuthSuccess } from '../../interfaces/AuthSuccess.interface';
import { User } from '../../../../interfaces/user.interface';
import { NgIf } from '@angular/common';
import { HttpErrorResponse } from '@angular/common/http';

@Component({
  selector: 'app-signup',
  imports: [MatIconModule, ReactiveFormsModule, BanniereComponent, NgIf],
  templateUrl: './signup.component.html',
  styleUrl: './signup.component.css',
})
export class SignupComponent {
  authService = inject(AuthService);
  form: FormGroup;
  errorMessage?: string;
  constructor(
    private router: Router,
    private fb: FormBuilder,
    private apiService: ApiService
  ) {
    this.form = this.fb.group({
      userName: ['', [Validators.required]],
      email: ['', [Validators.required, Validators.email]],
      password: [
        '',
        [
          Validators.required,
          Validators.min(8),
          Validators.pattern(
            /^(?=.*[a-z])(?=.*[A-Z])(?=.*\d)(?=.*[@$!%*?&])[A-Za-z\d@$!%*?&]{8,}$/
          ), // Demande au moins un chiffre, lettre minuscule, lettre majuscule et caractère spécial
        ],
      ],
    });
  }

  registration() {
    if (this.form.invalid) {
      return;
    }
    const registerRequest = this.form.value as RegisterRequest;
    this.authService.register(registerRequest).subscribe({
      next: (response: AuthSuccess) => {
        localStorage.setItem('token', response.token);
        this.authService.me().subscribe((user: User) => {
          this.apiService.logIn(user);
          this.router.navigate(['/themes']);
        });
        this.router.navigate(['/themes']);
      },
      error: (error: HttpErrorResponse) => {
        if (error.error.message) {
          this.errorMessage = error.error.message;
        }
      },
    });
  }
  navigateBack() {
    this.router.navigate(['/']);
  }
}
