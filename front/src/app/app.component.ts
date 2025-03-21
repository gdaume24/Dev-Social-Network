import { Component } from '@angular/core';
import { RouterOutlet } from '@angular/router';
import { MatIconModule } from '@angular/material/icon';
import { MatSidenavModule } from '@angular/material/sidenav';

@Component({
  selector: 'app-root',
  imports: [RouterOutlet, MatIconModule, MatSidenavModule],
  templateUrl: './app.component.html',
  styleUrl: './app.component.css',
})
export class AppComponent {
  // Affiche la bannière seulement si on est pas sur la landing-page
}
