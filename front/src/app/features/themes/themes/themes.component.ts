import { Component, inject } from '@angular/core';
import { BanneerConnectedComponent } from '../../../shared/banneer-connected/banneer-connected.component';
import { ThemeService } from '../services/theme.service';
import { MatCardModule } from '@angular/material/card';
import { AsyncPipe, NgFor } from '@angular/common';

@Component({
  selector: 'app-themes',
  standalone: true,
  imports: [BanneerConnectedComponent, MatCardModule, NgFor, AsyncPipe],
  templateUrl: './themes.component.html',
  styleUrls: ['./themes.component.css'],
})
export class ThemesComponent {
  private themeService = inject(ThemeService);
  themes = this.themeService.themes;

  ngOnInit() {
    this.themeService.getAll().subscribe();
  }
}
