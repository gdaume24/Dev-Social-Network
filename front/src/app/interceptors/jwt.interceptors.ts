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
    const authExcludedEndpoints = ['api/auth/register', 'api/auth/login'];
    const isExcluded = authExcludedEndpoints.some(
      (url) => request.url.endsWith(url) // Use endsWith to match the exact endpoint
    );
    console.log('Is Excluded:', isExcluded);
    console.log('Request URL:', request.url);
    if (isExcluded) {
      console.log('********** Excluded **********');
      return next.handle(request); // Skip adding the Authorization header
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
      }
    }
    return next.handle(request);
  }
}
