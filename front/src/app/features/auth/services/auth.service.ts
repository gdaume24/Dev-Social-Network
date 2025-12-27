import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';
import { AuthSuccess } from '../interfaces/AuthSuccess.interface';
import { LoginRequest } from '../interfaces/LoginRequest.interface';
import { HttpClient } from '@angular/common/http';
import { User } from '../../../interfaces/user.interface';
import { RegisterRequest } from '../interfaces/RegisterRequest.interface';
import { environment } from '../../../../environments/environment';

@Injectable({
  providedIn: 'root',
})
export class AuthService {
  private pathService = `${environment.apiUrl}/auth`;

  constructor(private httpClient: HttpClient) {
    console.log('pathService =', this.pathService);
  }

  register(registerRequest: RegisterRequest): Observable<AuthSuccess> {
    return this.httpClient.post<AuthSuccess>(
      `${this.pathService}/register`,
      registerRequest
    );
  }
  login(loginRequest: LoginRequest): Observable<AuthSuccess> {
    return this.httpClient.post<AuthSuccess>(
      `${this.pathService}/login`,
      loginRequest
    );
  }
  me(): Observable<User> {
    return this.httpClient.get<User>(`${this.pathService}/me`);
  }
}
