import { Component } from '@angular/core';
import { BanneerConnectedComponent } from '../../shared/banneer-connected/banneer-connected.component';
import { User } from '../../interfaces/user.interface';
import { AuthService } from '../auth/services/auth.service';
import { ThemeService } from '../themes/services/theme.service';
import { NgFor } from '@angular/common';
import {
  FormBuilder,
  FormGroup,
  ReactiveFormsModule,
  Validators,
} from '@angular/forms';
import { MatSnackBar } from '@angular/material/snack-bar';
import { UserService } from '../user/user.service';

@Component({
  selector: 'app-profile',
  imports: [BanneerConnectedComponent, NgFor, ReactiveFormsModule],
  templateUrl: './profile.component.html',
  styleUrl: './profile.component.css',
})
export class ProfileComponent {
  profileForm!: FormGroup;
  user: any;
  subscribedThemes: any;

  constructor(
    private fb: FormBuilder,
    private authService: AuthService,
    private userService: UserService,
    private themeService: ThemeService,
    private snackBar: MatSnackBar
  ) {}

  ngOnInit() {
    this.initForm();
    this.loadUserData();
    this.loadSubscribedThemes();
  }
  private initForm(): void {
    this.profileForm = this.fb.group({
      userName: ['', [Validators.maxLength(20)]],
      email: ['', [Validators.email]],
      password: ['', [Validators.minLength(8)]],
    });
  }
  private loadUserData(): void {
    this.authService.me().subscribe({
      next: (data: User) => {
        this.user = data;
        this.profileForm.patchValue({
          userName: this.user.userName,
          email: this.user.email,
        });
      },
    });
  }
  private loadSubscribedThemes(): void {
    this.themeService.getSubscribedThemes().subscribe({
      next: (data) => {
        this.subscribedThemes = data;
      },
    });
  }

  save() {
    const formValues = this.profileForm.value;
    if (formValues.userName || formValues.email || formValues.password) {
      const updatedUser = {
        userName: formValues.userName || this.user.userName,
        email: formValues.email || this.user.email,
        password: formValues.password || null,
      };
      this.userService.updateUser(updatedUser).subscribe({
        next: (response) => {
          const newToken = response.jwt; // Récupérer le nouveau JWT
          localStorage.setItem('token', newToken); // Remplacer l'ancien token
          this.showSnackBar('Vos informations ont été modifiées avec succès !');
        },
      });
    } else {
      this.showSnackBar('Veuillez remplir correctement le formulaire.');
    }
  }

  unsubscribe(themeId: string): void {
    this.themeService.unsubscribeFromTheme(themeId).subscribe({
      next: () => {
        this.loadSubscribedThemes(); // Met à jour la liste localement
      },
    });
  }

  private showSnackBar(message: string): void {
    this.snackBar.open(message, '', {
      duration: 3000, // Durée d'affichage en millisecondes
      horizontalPosition: 'center', // Position horizontale
      verticalPosition: 'top', // Position verticale
    });
  }
}
