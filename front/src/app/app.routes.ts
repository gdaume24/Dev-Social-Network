import { Routes } from '@angular/router';
import { LandingPageComponent } from './features/auth/pages/landing-page/landing-page.component';
import { SignupComponent } from './features/auth/pages/signup/signup.component';
import { SigninComponent } from './features/auth/pages/signin/signin.component';
import { ThemesComponent } from './features/themes/pages/themes/themes.component';

export const routes: Routes = [
  {
    path: '',
    component: LandingPageComponent,
  },
  {
    path: 'signup',
    component: SignupComponent,
  },
  {
    path: 'signin',
    component: SigninComponent,
  },
  {
    path: 'themes',
    component: ThemesComponent,
  },
];
