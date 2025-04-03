import { Component } from '@angular/core';
import { Article } from '../../interface/article.interface';
import { ArticleService } from '../../service/article.service';
import { ActivatedRoute, Router } from '@angular/router';
import { BanneerConnectedComponent } from '../../../../shared/banneer-connected/banneer-connected.component';
import { CommonModule } from '@angular/common';
import { MatIconModule } from '@angular/material/icon';
import {
  FormBuilder,
  FormControl,
  FormGroup,
  FormsModule,
  ReactiveFormsModule,
  Validators,
} from '@angular/forms';
import { CommentRequest } from '../../interface/commentRequest.interface';
import { CommentsReponse } from '../../interface/commentsReponse.interface';

@Component({
  selector: 'app-articledetail',
  imports: [
    BanneerConnectedComponent,
    CommonModule,
    MatIconModule,
    FormsModule,
    ReactiveFormsModule,
  ],
  templateUrl: './articledetail.component.html',
  styleUrl: './articledetail.component.css',
})
export class ArticledetailComponent {
  article?: Article;
  articleSubscription: any;
  commentsSubscription: any;
  comments: CommentsReponse[] = [];
  formGroup = new FormGroup({
    comment: new FormControl('', [Validators.required]),
  });

  constructor(
    private route: ActivatedRoute,
    private articleService: ArticleService,
    private router: Router
  ) {}

  ngOnInit() {
    const articleId = this.route.snapshot.params['id'];
    this.articleSubscription = this.articleService
      .getArticleById(articleId)
      .subscribe((article) => {
        this.article = article;
      });
    this.commentsSubscription = this.articleService
      .getComments(articleId)
      .subscribe((comments) => {
        this.comments = comments;
      });
  }
  navigateBack(): void {
    this.router.navigate(['/articles']);
  }
  postComment() {
    const comment = this.formGroup.value.comment || ''; // Extract comment as string
    this.articleService
      .postComment(this.article?.id?.toString() || '', comment)
      .subscribe({
        next: (response) => {
          // Réinitialiser le champ de texte
          this.formGroup.reset();

          // Ajouter dynamiquement le commentaire à la liste
          this.refreshComments();
        },
      });
  }
  refreshComments() {
    const articleId = this.article?.id?.toString() || '';
    this.commentsSubscription = this.articleService
      .getComments(articleId)
      .subscribe((comments) => {
        this.comments = comments;
      });
  }
  ngOnDestroy() {
    if (this.articleSubscription) {
      this.articleSubscription.unsubscribe();
    }
    if (this.commentsSubscription) {
      this.commentsSubscription.unsubscribe();
    }
  }
}
