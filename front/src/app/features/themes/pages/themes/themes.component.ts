import { Component, inject } from '@angular/core';
import { AsyncPipe, NgFor } from '@angular/common';
import { BanneerConnectedComponent } from '../../../../shared/banneer-connected/banneer-connected.component';
import { ThemeService } from '../../services/theme.service';
import { ThemeComponent } from '../../components/theme/theme.component';

@Component({
  selector: 'app-themes',
  standalone: true,
  imports: [BanneerConnectedComponent, ThemeComponent],
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
