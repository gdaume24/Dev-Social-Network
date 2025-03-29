import { Component } from '@angular/core';
import { Article } from '../../interface/article.interface';
import { ArticleService } from '../../service/article.service';
import { ActivatedRoute, Router } from '@angular/router';
import { BanneerConnectedComponent } from '../../../../shared/banneer-connected/banneer-connected.component';
import { CommonModule } from '@angular/common';
import { MatIconModule } from '@angular/material/icon';
import { FormsModule, NgModel } from '@angular/forms';

@Component({
  selector: 'app-articledetail',
  imports: [
    BanneerConnectedComponent, 
    CommonModule, 
    MatIconModule,
    FormsModule
  ],
  templateUrl: './articledetail.component.html',
  styleUrl: './articledetail.component.css'
})
export class ArticledetailComponent {
  article? : Article;
  subscription: any;
  newComment: string = '';

  constructor(private route: ActivatedRoute, private articleService: ArticleService, private router: Router) {}
  
  ngOnInit() {
    const articleId = this.route.snapshot.params['id'];
    this.subscription = this.articleService.getArticleById(articleId).subscribe((article) => {
      this.article = article;
      console.log(this.article);
    });
  }
  navigateBack(): void {
    this.router.navigate(['/articles']);
  }
  ngOnDestroy() {
    this.subscription.unsubscribe(); // Unsubscribe to avoid memory leaks
  }
}
