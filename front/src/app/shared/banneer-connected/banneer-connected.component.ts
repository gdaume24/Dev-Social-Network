import { Component } from '@angular/core';
import { MatIconModule } from '@angular/material/icon';
import { NavigationStart, Router } from '@angular/router';

@Component({
  selector: 'app-banneer-connected',
  imports: [MatIconModule],
  templateUrl: './banneer-connected.component.html',
  styleUrl: './banneer-connected.component.css',
})
export class BanneerConnectedComponent {
  themeRoute = false;
  articleRoute = false;
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
}
