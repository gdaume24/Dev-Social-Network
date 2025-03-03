import { Component, Input } from '@angular/core';
import { MatCardModule } from '@angular/material/card';
import { ThemeService } from '../../services/theme.service';
import { NgClass } from '@angular/common';

@Component({
  selector: 'app-theme',
  imports: [MatCardModule, NgClass],
  templateUrl: './theme.component.html',
  styleUrl: './theme.component.css',
})
export class ThemeComponent {
  @Input() id = '';
  @Input() titre = '';
  @Input() description = '';
  isSubscribed = false;

  constructor(private themeService: ThemeService) {}

  ngOnInit(): void {
    // Vérifie si l'utilisateur est déjà abonné au thème
    this.themeService.isSubscribedToTheme(this.id).subscribe((isSubscribed) => {
      this.isSubscribed = isSubscribed;
    });
  }

  onSubscribe() {
    if (!this.isSubscribed) {
      this.themeService.subscribeToTheme(this.id).subscribe((response) => {
        console.log('Subscribed to theme:', response);
      });
    } else {
      this.onUnsubscribe();
    }
  }

  onUnsubscribe() {
    this.themeService.unsubscribeFromTheme(this.id).subscribe((response) => {
      console.log('Unsubscribed from theme:', response);
      this.isSubscribed = false; // Mettre à jour l'état d'abonnement
    });
  }
}
