import { Component, inject } from '@angular/core';
import { FormBuilder, FormControl, FormGroup, ReactiveFormsModule, Validators } from '@angular/forms';
import { MatIconModule } from '@angular/material/icon';
import { Router } from '@angular/router';
import { AuthService } from '../../services/auth.service';
import { BanniereComponent } from '../../components/banniere/banniere.component';
import { LoginRequest } from '../../interfaces/LoginRequest.interface';
import { AuthSuccess } from '../../interfaces/AuthSuccess.interface';
import { User } from '../../../../interfaces/user.interface';
import { ApiService } from '../../../../service/api.service';

@Component({
  selector: 'app-signin',
  imports: [MatIconModule, ReactiveFormsModule, BanniereComponent],
  templateUrl: './signin.component.html',
  styleUrl: './signin.component.css'
})
export class SigninComponent {
  authService = inject(AuthService);
  form: FormGroup;

  constructor(
    private router: Router,
    private fb: FormBuilder,
    private apiService: ApiService
  ) {
      this.form = this.fb.group({
        email: ['', [Validators.required, Validators.email]],
        password: ['', [Validators.required, Validators.min(3)]]
      });
    }

  connection() {
    const loginRequest = this.form.value as LoginRequest;
    this.authService.login(loginRequest).subscribe(
      (response: AuthSuccess) => {
        localStorage.setItem('token', response.token);
        this.authService.me().subscribe((user: User) => {
          this.apiService.logIn(user);
          this.router.navigate(['/articles'])
        });
        this.router.navigate(['/articles'])
      }
    );
  }
  navigateBack() {
    this.router.navigate(['/']);
  }
}
