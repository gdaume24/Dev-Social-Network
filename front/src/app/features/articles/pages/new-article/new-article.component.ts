import { Component } from '@angular/core';
import { MatIconModule } from '@angular/material/icon';
import { Router } from '@angular/router';
import { BanneerConnectedComponent } from '../../../../shared/banneer-connected/banneer-connected.component';
import { Theme } from '../../../themes/interfaces/theme.interface';
import { ThemeService } from '../../../themes/services/theme.service';
import { NgFor, NgIf } from '@angular/common';

@Component({
  selector: 'app-new-article',
  imports: [MatIconModule, BanneerConnectedComponent, NgFor, NgIf],
  templateUrl: './new-article.component.html',
  styleUrl: './new-article.component.scss'
})
export class NewArticleComponent {
  themes: Theme[] = [];
  showSuggestions = false;
  selectedTheme: string = '';

  constructor(
    private router: Router,
    private themeService: ThemeService
  ) {}

  ngOnInit(): void {
    this.loadThemes();
  }
  selectTheme(theme: Theme): void {
    this.selectedTheme = theme.name; // Met à jour le thème sélectionné
    this.showSuggestions = false; // Ferme la liste déroulante
  }
  loadThemes(): void {
    this.themeService.getAll().subscribe({
      next: (themes) => {
        this.themes = themes; // Met à jour la liste des thèmes
      }
    })
  }
  navigateBack() {
    this.router.navigate(['/articles']);
  }
}
