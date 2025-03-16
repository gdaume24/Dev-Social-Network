import { Component } from '@angular/core';
import { MatIconModule } from '@angular/material/icon';
import { NavigationStart, Router } from '@angular/router';
import { MatMenuModule } from '@angular/material/menu';
import { MatSidenavModule } from '@angular/material/sidenav';
import { FormsModule } from '@angular/forms';

@Component({
  selector: 'app-banneer-connected',
  imports: [MatIconModule, MatMenuModule, MatSidenavModule, FormsModule],
  templateUrl: './banneer-connected.component.html',
  styleUrl: './banneer-connected.component.css',
})
export class BanneerConnectedComponent {
  themeRoute = false;
  articleRoute = false;
  isToggled: boolean = false;
  constructor(private router: Router) {}
  ngOnInit(): void {
    this.themeRoute = this.router?.url === '/themes';
    this.articleRoute = this.router?.url === '/articles';
  }

  navigateToThemes() {
    this.router.navigate(['/themes']);
  }
  navigateToArticles() {
    this.router.navigate(['/articles']);
  }
  navigateToProfile() {
    this.router.navigate(['/profile']);
  }
  logout() {
    // Clear authentication data (e.g., tokens)
    localStorage.removeItem('authToken');
    sessionStorage.clear();

    // Redirect to login or home page
    this.router.navigate(['']);
  }
  toggle() {
    this.isToggled = !this.isToggled;
  }
  closeToggle() {
    this.isToggled = false;
  }

}
