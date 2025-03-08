import { HttpClient } from '@angular/common/http';
import { inject, Injectable, signal } from '@angular/core';
import { Observable, switchMap, tap } from 'rxjs';
import { Theme } from '../interfaces/theme.interface';
import { AuthService } from '../../auth/services/auth.service';

@Injectable({
  providedIn: 'root',
})
export class ThemeService {
  private httpClient = inject(HttpClient);
  private authService = inject(AuthService);
  private pathService = 'api/toto';
  private pathThemeService = 'api/themes';
  themes = signal<Theme[]>([]);
  public getAll(): Observable<Theme[]> {
    return this.httpClient
      .get<Theme[]>('api/themes')
      .pipe(tap((themes) => this.themes.set(themes)));
  }

  public subscribeToTheme(themeId: string): Observable<any> {
    return this.authService.me().pipe(
      switchMap((user) => {
        const userId = user.id;
        return this.httpClient.post(
          `${this.pathService}/${userId}/subscribe/${themeId}`,
          {}
        );
      })
    );
  }

  public unsubscribeFromTheme(themeId: string): Observable<any> {
    return this.authService.me().pipe(
      switchMap((user) => {
        const userId = user.id;
        return this.httpClient.post(
          `${this.pathService}/${userId}/unsubscribe/${themeId}`,
          {}
        );
      })
    );
  }

  public isSubscribedToTheme(themeId: string): Observable<boolean> {
    return this.authService.me().pipe(
      switchMap((user) => {
        const userId = user.id;
        return this.httpClient.get<boolean>(
          `${this.pathService}/${userId}/isSubscribed/${themeId}`
        );
      })
    );
  }
}
