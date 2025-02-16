import { Component } from '@angular/core';
import { NavigationStart, Router, RouterOutlet } from '@angular/router';
import { NgIf } from '@angular/common';
import { BanniereComponent } from './component/banniere/banniere.component';
import { MatIconModule } from '@angular/material/icon';

@Component({
  selector: 'app-root',
  imports: [RouterOutlet, BanniereComponent, NgIf, MatIconModule],
  templateUrl: './app.component.html',
  styleUrl: './app.component.css',
})
export class AppComponent {
  // Affiche la bannière seulement si on est pas sur la landing-page
  isLandingPage = true;
  constructor(private router: Router) {
    this.isLandingPage = this.router?.url === '/';
    router.events.forEach((event) => {
      if (event instanceof NavigationStart) {
        if (event['url'] === '/') {
          this.isLandingPage = true;
        }
        else {
          this.isLandingPage = false;
        }
      }
    });
  }
}
