import { Component, computed, inject, Input } from '@angular/core';
import { Router } from '@angular/router';

@Component({
  selector: 'app-landing-page',
  imports: [],
  templateUrl: './landing-page.component.html',
  styleUrl: './landing-page.component.css'
})
export class LandingPageComponent {
  constructor(private router: Router) {}

  navigateToSignup() {
    this.router.navigate(['/signup']);
  }
  navigateToSignIn() {
    this.router.navigate(['/signin']);
  }
}
