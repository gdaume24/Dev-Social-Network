import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable, switchMap } from 'rxjs';
import { AuthService } from '../../auth/services/auth.service';

@Injectable({
  providedIn: 'root'
})
export class ArticleService {

  private apiUrl = 'api/articles'; // Remplacez par l'URL de votre API

  constructor(private http: HttpClient,
              private authService: AuthService
  ) {}

  createArticle(article: { title: string; content: string; theme: string }): Observable<any> {
    return this.authService.me().pipe(
          switchMap((user) => {
            const userId = user.id;
            return this.http.post(`${this.apiUrl}/${userId}`, article);
    }))
  }
}