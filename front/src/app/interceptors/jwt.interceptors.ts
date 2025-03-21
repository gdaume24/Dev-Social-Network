import { isPlatformBrowser } from '@angular/common';
import {
  HttpHandler,
  HttpInterceptor,
  HttpRequest,
} from '@angular/common/http';
import { Inject, Injectable, PLATFORM_ID } from '@angular/core';

@Injectable({ providedIn: 'root' })
export class JwtInterceptor implements HttpInterceptor {
  constructor(@Inject(PLATFORM_ID) private platformId: Object) {}

  intercept(request: HttpRequest<any>, next: HttpHandler) {
    const authExcludedEndpoints = ['/api/auth/register', '/api/auth/login'];
    if (authExcludedEndpoints.some((url) => request.url.includes(url))) {
      return next.handle(request);
    }
    if (isPlatformBrowser(this.platformId)) {
      const token = localStorage.getItem('token');
      if (token) {
        request = request.clone({
          url: request.url,
          setHeaders: {
            Authorization: `Bearer ${token}`,
          },
        });
      }}
    return next.handle(request);
  }
}



