import { Component, inject, signal } from '@angular/core';
import { RouterOutlet } from '@angular/router';
import { ApiService } from './service/api.service';
import { NgIf } from '@angular/common';

@Component({
  selector: 'app-root',
  imports: [NgIf, RouterOutlet],
  templateUrl: './app.component.html',
  styleUrl: './app.component.css'
})
export class AppComponent {
  title = 'front';
  private apiService = inject(ApiService);
  data = signal<string>('');

  fetchData() {
    this.apiService.getTestRequest().subscribe(response => {
      this.data.set(response);
    }, error => {
      console.error('Erreur lors de la récupération des données', error);
    });
  }
}
