import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable, switchMap, tap } from 'rxjs';
import { AuthService } from '../../auth/services/auth.service';
import { ArticleRequest } from '../interface/articleRequest.interface';
import { Article } from '../interface/article.interface';
import { CommentRequest } from '../interface/commentRequest.interface';
import { CommentsReponse } from '../interface/commentsReponse.interface';

@Injectable({
  providedIn: 'root',
})
export class ArticleService {
  private apiUrl = 'api/articles'; // Remplacez par l'URL de votre API

  constructor(private http: HttpClient, private authService: AuthService) {}

  all(): Observable<any> {
    return this.http.get<Article[]>(`${this.apiUrl}/subscribed`).pipe();
  }

  createArticle(article: ArticleRequest): Observable<any> {
    return this.http.post(`${this.apiUrl}/create`, article);
  }

  getArticleById(id: string): Observable<Article> {
    return this.http.get<Article>(`${this.apiUrl}/${id}`);
  }

  postComment(articleId: string, comment: string): Observable<any> {
    const commentRequest: CommentRequest = { comment }; // Crée un objet JSON
    return this.http.post(
      `${this.apiUrl}/${articleId}/comment`,
      commentRequest
    );
  }

  getComments(articleId: string): Observable<CommentsReponse[]> {
    return this.http.get<CommentsReponse[]>(
      `${this.apiUrl}/${articleId}/comments`
    );
  }
}
