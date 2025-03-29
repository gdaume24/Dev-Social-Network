import { Component, LOCALE_ID } from '@angular/core';
import { RouterOutlet } from '@angular/router';
import { MatIconModule } from '@angular/material/icon';
import { MatSidenavModule } from '@angular/material/sidenav';
import { DatePipe } from '@angular/common';
import { NgModel } from '@angular/forms';

@Component({
  selector: 'app-root',
  imports: [RouterOutlet, MatIconModule, MatSidenavModule, NgModel],
  providers: [{provide: LOCALE_ID, useValue: 'fr' }],
  templateUrl: './app.component.html',
  styleUrl: './app.component.css',
})
export class AppComponent {}
