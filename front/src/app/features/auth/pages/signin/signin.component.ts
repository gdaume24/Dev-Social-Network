import { Component, inject } from '@angular/core';
import { FormControl, FormGroup, ReactiveFormsModule } from '@angular/forms';
import { MatIconModule } from '@angular/material/icon';
import { Router } from '@angular/router';
import { AuthService } from '../../services/auth.service';
import { BanniereComponent } from '../../components/banniere/banniere.component';

@Component({
  selector: 'app-signin',
  imports: [MatIconModule, ReactiveFormsModule, BanniereComponent],
  templateUrl: './signin.component.html',
  styleUrl: './signin.component.css'
})
export class SigninComponent {
  authService = inject(AuthService);
  applyForm = new FormGroup({
    username: new FormControl(''),
    password: new FormControl('')
    });
  constructor(private router: Router) {}
  connection() {
  this.authService.connection(
    this.applyForm.value.username ?? '',
    this.applyForm.value.password ?? ''
  );
}
  navigateBack() {
    this.router.navigate(['/']);
  }
}
