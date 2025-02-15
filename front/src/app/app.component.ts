import { Component, inject, signal } from '@angular/core';
import { NavigationEnd, Router, RouterOutlet } from '@angular/router';
import { ApiService } from './service/api.service';
import { NgIf } from '@angular/common';
import { MatSlideToggleModule } from '@angular/material/slide-toggle';
import { ResponsiveService } from './service/responsive.service';
import { BanniereComponent } from './component/banniere/banniere.component';
import { filter } from 'rxjs';

@Component({
  selector: 'app-root',
  imports: [RouterOutlet, BanniereComponent, NgIf],
  templateUrl: './app.component.html',
  styleUrl: './app.component.css',
})
export class AppComponent {
  isLandingPage = true;
  constructor(private router: Router) {
  this.isLandingPage = this.router?.url === '/'
  }
}
