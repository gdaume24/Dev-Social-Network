import { Component } from '@angular/core';
import { MatIconModule } from '@angular/material/icon';
import { Router } from '@angular/router';
import { BanneerConnectedComponent } from '../../../../shared/banneer-connected/banneer-connected.component';

@Component({
  selector: 'app-new-article',
  imports: [MatIconModule, BanneerConnectedComponent],
  templateUrl: './new-article.component.html',
  styleUrl: './new-article.component.scss'
})
export class NewArticleComponent {

  constructor(private router: Router) {}

  navigateBack() {
    this.router.navigate(['/articles']);
  }
}
