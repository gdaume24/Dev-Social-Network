import { Component, inject } from '@angular/core';
import { ArticleComponent } from '../../components/article/article.component';
import { BanneerConnectedComponent } from '../../../../shared/banneer-connected/banneer-connected.component';
import { Router } from '@angular/router';
import { ArticleService } from '../../service/article.service';

@Component({
  selector: 'app-articles',
  imports: [BanneerConnectedComponent, ArticleComponent],
  templateUrl: './articles.component.html',
  styleUrl: './articles.component.css'
})
export class ArticlesComponent {

  articleService = inject(ArticleService);
  articles = [];

  constructor(private router: Router,
  ) {}

  ngOnInit() {
    this.articleService.all().subscribe((articles) => {
      this.articles = articles
    })
  }

  goToCreateArticle() {
    this.router.navigate(['/new-article']);
  }
}
