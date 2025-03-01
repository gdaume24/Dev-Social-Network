import { Injectable } from '@angular/core';
import { CanActivate, CanActivateFn, Router } from '@angular/router';
import { ApiService } from '../service/api.service';

@Injectable({providedIn: 'root'})
export class AuthGuard implements CanActivate {

  constructor( 
    private router: Router,
    private apiService: ApiService,
  ) {
  }

  public canActivate(): boolean {
    if (!this.apiService.isLogged) {
      this.router.navigate(['login']);
      return false;
    }
    return true;
  }
}