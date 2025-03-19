import { Component } from '@angular/core';
import { MatIconModule } from '@angular/material/icon';
import { Router } from '@angular/router';
import { BanneerConnectedComponent } from '../../../../shared/banneer-connected/banneer-connected.component';
import { Theme } from '../../../themes/interfaces/theme.interface';
import { ThemeService } from '../../../themes/services/theme.service';
import { NgFor, NgIf, NgStyle } from '@angular/common';

@Component({
  selector: 'app-new-article',
  imports: [MatIconModule, BanneerConnectedComponent, NgFor, NgIf, NgStyle],
  templateUrl: './new-article.component.html',
  styleUrls: ['./new-article.component.scss']
})
export class NewArticleComponent {
  themes: Theme[] = [];
  isClicked = false;

  constructor(
    private router: Router,
    private themeService: ThemeService
  ) {}

  ngOnInit(): void {
    this.loadThemes();
  }
  loadThemes(): void {
    this.themeService.getAll().subscribe({
      next: (themes) => {
        this.themes = themes; // Updates the list of themes
      }
    });
  }
  navigateBack(): void {
    this.router.navigate(['/articles']);
  }
}