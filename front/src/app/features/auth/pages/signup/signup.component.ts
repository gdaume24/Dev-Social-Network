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

@Component({
  selector: 'app-signup',
  imports: [MatIconModule, ReactiveFormsModule, BanniereComponent],
  templateUrl: './signup.component.html',
  styleUrl: './signup.component.css',
})
export class SignupComponent {
  authService = inject(AuthService);
  form: FormGroup;
  constructor(
    private router: Router,
    private fb: FormBuilder,
    private apiService: ApiService
  ) {
    this.form = this.fb.group({
      userName: ['', [Validators.required]],
      email: ['', [Validators.required, Validators.email]],
      password: ['', [Validators.required, Validators.min(3)]],
    });
  }

  registration() {
    const registerRequest = this.form.value as RegisterRequest;
    this.authService
      .register(registerRequest)
      .subscribe((response: AuthSuccess) => {
        localStorage.setItem('token', response.token);
        this.authService.me().subscribe((user: User) => {
          this.apiService.logIn(user);
          this.router.navigate(['/themes']);
        });
        this.router.navigate(['/themes']);
      });
  }
  navigateBack() {
    this.router.navigate(['/']);
  }
}
