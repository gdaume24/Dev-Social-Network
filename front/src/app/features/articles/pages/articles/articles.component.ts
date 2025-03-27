import { Component, inject } from '@angular/core';
import { ArticleComponent } from '../../components/article/article.component';
import { BanneerConnectedComponent } from '../../../../shared/banneer-connected/banneer-connected.component';
import { Router } from '@angular/router';
import { ArticleService } from '../../service/article.service';
import { Article } from '../../interface/article.interface';
import { NgFor, DatePipe } from '@angular/common';

@Component({
  selector: 'app-articles',
  imports: [BanneerConnectedComponent, ArticleComponent, NgFor],
  templateUrl: './articles.component.html',
  styleUrl: './articles.component.css'
})
export class ArticlesComponent {

  articleService = inject(ArticleService);
  articles: Article[] = [];

  constructor(private router: Router,
  ) {}

  ngOnInit() {
    this.articleService.all().subscribe((articles) => {
      this.articles = articles.map((article: Article) => ({
        ...article,
        date: new Date(article.date) // Convertit la chaîne en objet Date
      }))
      console.log(this.articles);
    });
  };

  goToCreateArticle() {
    this.router.navigate(['/new-article']);
  }
}
