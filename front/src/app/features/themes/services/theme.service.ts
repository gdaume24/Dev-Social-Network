import { HttpClient } from '@angular/common/http';
import { inject, Injectable, signal } from '@angular/core';
import { Observable, tap } from 'rxjs';
import { Theme } from '../interfaces/theme.interface';

@Injectable({
  providedIn: 'root',
})
export class ThemeService {
  private httpClient = inject(HttpClient);
  private pathService = 'api/themes';
  themes = signal<Theme[]>([]);
  public getAll(): Observable<Theme[]> {
    return this.httpClient
      .get<Theme[]>(this.pathService)
      .pipe(tap((themes) => this.themes.set(themes)));
  }

  public subscribeToTheme(themeid: string): Observable<any> {

}

