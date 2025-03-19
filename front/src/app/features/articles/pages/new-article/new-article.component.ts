import { Component } from '@angular/core';
import { MatIconModule } from '@angular/material/icon';
import { Router } from '@angular/router';
import { BanneerConnectedComponent } from '../../../../shared/banneer-connected/banneer-connected.component';
import { Theme } from '../../../themes/interfaces/theme.interface';
import { ThemeService } from '../../../themes/services/theme.service';
import { NgFor, NgIf, NgStyle } from '@angular/common';
import { ArticleService } from '../../service/article.service';
import { FormControl, FormGroup, ReactiveFormsModule, Validators } from '@angular/forms';
import { ArticleRequest } from '../../interface/articleRequest.interface';

@Component({
  selector: 'app-new-article',
  imports: [MatIconModule, BanneerConnectedComponent, NgFor, NgIf, NgStyle, ReactiveFormsModule],
  templateUrl: './new-article.component.html',
  styleUrls: ['./new-article.component.scss']
})
export class NewArticleComponent {
  themes: Theme[] = [];
  isClicked = false;
  selectedTheme = '';
  title = '';
  content = '';
  formGroup = new FormGroup
  ({
      theme: new FormControl('', [Validators.required]),
      title: new FormControl('', [Validators.required]),
      content: new FormControl('', [Validators.required]),
  });

  constructor(
    private router: Router,
    private themeService: ThemeService,
    private articleService: ArticleService
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
  createArticle(): void {
    console.log(this.formGroup.value);
    const articleRequest = this.formGroup.value as ArticleRequest;
    this.articleService.createArticle(articleRequest).subscribe({
      next: () => {
        alert('Article créé avec succès !');
        this.router.navigate(['/articles']); // Redirige vers la liste des articles
      },
      error: (err) => {
        console.error('Erreur lors de la création de l\'article :', err);
        alert('Une erreur est survenue lors de la création de l\'article.');
      },
    });
  }
  navigateBack(): void {
    this.router.navigate(['/articles']);
  }
}