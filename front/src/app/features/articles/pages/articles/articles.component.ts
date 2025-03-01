import { Component } from '@angular/core';
import { ArticleComponent } from '../../components/article/article.component';
import { BanneerConnectedComponent } from '../../../../shared/banneer-connected/banneer-connected.component';

@Component({
  selector: 'app-articles',
  imports: [BanneerConnectedComponent, ArticleComponent],
  templateUrl: './articles.component.html',
  styleUrl: './articles.component.css'
})
export class ArticlesComponent {

}
