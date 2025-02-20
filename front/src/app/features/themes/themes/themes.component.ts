import { Component, inject } from '@angular/core';
import { BanneerConnectedComponent } from '../../../shared/banneer-connected/banneer-connected.component';
import { ThemeService } from '../services/theme.service';

@Component({
  selector: 'app-themes',
  imports: [BanneerConnectedComponent],
  templateUrl: './themes.component.html',
  styleUrl: './themes.component.css',
})
export class ThemesComponent {
  private themeService = inject(ThemeService);
  themes = this.themeService.themes;

  ngOnInit() {
    this.themeService.getAll().subscribe();
  }
}
