import { Component } from '@angular/core';
import { BanneerConnectedComponent } from '../../shared/banneer-connected/banneer-connected.component';
import { User } from '../../interfaces/user.interface';
import { AuthService } from '../auth/services/auth.service';
import { ThemeService } from '../themes/services/theme.service';
import { NgFor } from '@angular/common';

@Component({
  selector: 'app-profile',
  imports: [BanneerConnectedComponent, NgFor],
  templateUrl: './profile.component.html',
  styleUrl: './profile.component.css'
})
export class ProfileComponent {
  user: any;
  subscribedThemes: any;

  constructor(private authService: AuthService, private themeService: ThemeService) {}

  ngOnInit() {
    this.authService.me().subscribe({
      next: (data: User) => {
        this.user = data;
        console.log(this.user);
      }
    })
  this.themeService.getSubscribedThemes().subscribe({
    next: (data) => {
      this.subscribedThemes = data;
      console.log(this.subscribedThemes);
      }
    })
  }

  save() {
    // Implement save logic here
    console.log('Save clicked');
  }

  unsubscribe(themeId: string): void {
    this.themeService.unsubscribeFromTheme(themeId).subscribe({
      next: () => {
        console.log(`Désabonné du thème avec l'ID : ${themeId}`);
        this.refreshSubscribedThemes();// Met à jour la liste localement
      }
    });
  }

  private refreshSubscribedThemes(): void {
    this.themeService.getSubscribedThemes().subscribe({
      next: (data) => {
        this.subscribedThemes = data;
        console.log('Thèmes abonnés mis à jour :', this.subscribedThemes);
      },
      error: (err) => {
        console.error('Erreur lors du chargement des thèmes abonnés :', err);
      },
    });
  }
}
