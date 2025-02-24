import { Component, Input } from '@angular/core';
import { MatCardModule } from '@angular/material/card';
import { ThemeService } from '../../services/theme.service';

@Component({
  selector: 'app-theme',
  imports: [MatCardModule],
  templateUrl: './theme.component.html',
  styleUrl: './theme.component.css',
})
export class ThemeComponent {
  @Input() id = '';
  @Input() titre = '';
  @Input() description = '';

  constructor(private themeService: ThemeService) {}

  onSubscribe() {
    this.themeService.subscribeToTheme(this.id).subscribe((response) => {
      console.log('Subscribed to theme:', response);
    });
  }
}
