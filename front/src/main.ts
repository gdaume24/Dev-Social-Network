import { registerLocaleData } from '@angular/common';
import localeFr from '@angular/common/locales/fr';

/// <reference types="@angular/localize" />

registerLocaleData(localeFr, 'fr');

import { bootstrapApplication } from '@angular/platform-browser';
import { appConfig } from './app/app.config';
import { AppComponent } from './app/app.component';

bootstrapApplication(AppComponent, appConfig).catch((err) =>
  console.error(err)
);
