import { Component } from '@angular/core';
import { ArticleComponent } from '../../components/article/article.component';
import { BanneerConnectedComponent } from '../../../../shared/banneer-connected/banneer-connected.component';
import { Router } from '@angular/router';

@Component({
  selector: 'app-articles',
  imports: [BanneerConnectedComponent, ArticleComponent],
  templateUrl: './articles.component.html',
  styleUrl: './articles.component.css'
})
export class ArticlesComponent {

  constructor(private router: Router) {}

  goToCreateArticle() {
    this.router.navigate(['/new-article']);
  }
}
