import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable, switchMap } from 'rxjs';
import { AuthService } from '../../auth/services/auth.service';
import { ArticleRequest } from '../interface/articleRequest.interface';
import { Article } from '../interface/article.interface';

@Injectable({
  providedIn: 'root'
})
export class ArticleService {

  private apiUrl = 'api/articles'; // Remplacez par l'URL de votre API

  constructor(private http: HttpClient,
              private authService: AuthService
  ) {}

  all(): Observable<any> {
    return this.http.get<Article[]>(`${this.apiUrl}/subscribed`);
  }

  createArticle(article: ArticleRequest): Observable<any> {
    return this.authService.me().pipe(
          switchMap((user) => {
            const userId = user.id;
            return this.http.post(`${this.apiUrl}/create/${userId}`, article);
    }))
  }
}