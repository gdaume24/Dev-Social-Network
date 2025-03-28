import { Component, inject } from '@angular/core';
import { BanneerConnectedComponent } from '../../../../shared/banneer-connected/banneer-connected.component';
import { Router } from '@angular/router';
import { ArticleService } from '../../service/article.service';
import { Article } from '../../interface/article.interface';
import { NgFor, CommonModule, NgIf } from '@angular/common';

@Component({
  selector: 'app-articles',
  imports: [BanneerConnectedComponent, NgFor, CommonModule, NgIf],
  templateUrl: './articles.component.html',
  styleUrl: './articles.component.css',
})
export class ArticlesComponent {
  articleService = inject(ArticleService);
  articles: Article[] = [];
  sortOptions = ['date', 'titre']; // Options de tri
  currentSort: string | null = null;
  sortAscending = true; // Indique si le tri est croissant ou décroissant
  isDropdownOpen = false; // État du menu déroulant

  constructor(private router: Router) {}

  ngOnInit() {
    this.articleService.all().subscribe((articles) => {
      this.articles = articles.map((article: Article) => ({
        ...article,
        date: new Date(article.date), // Convertit la chaîne en objet Date
      }));
      console.log("date is of type : ",  this.articles[0].date instanceof Date); // Affiche la date de l'article
    });
  }

  goToCreateArticle() {
    this.router.navigate(['/new-article']);
  }
  toggleDropdown() {
    this.isDropdownOpen = !this.isDropdownOpen;
  }
  sortBy(sortKey: string) {
    if (this.currentSort === sortKey) {
      // Même critère : on inverse l'ordre
      this.sortAscending = !this.sortAscending;
    } else {
      // Nouveau critère : ordre ascendant par défaut
      this.currentSort = sortKey;
      this.sortAscending = true;
    }

    this.sortArticles();
    this.isDropdownOpen = false; // Ferme le dropdown après sélection
  }

  sortArticles() {
    if (!this.currentSort) return;

    this.articles.sort((a, b) => {
      let comparison = 0;

      if (this.currentSort === 'date') {
        comparison = a.date.getTime() - b.date.getTime();
      } else if (this.currentSort === 'titre') {
        comparison = a.title.localeCompare(b.title);
      }

      // Inverser l'ordre si nécessaire
      return this.sortAscending ? comparison : -comparison;
    });
  }
}
