import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';
import { AuthSuccess } from '../interfaces/AuthSuccess.interface';
import { LoginRequest } from '../interfaces/LoginRequest.interface';
import { HttpClient } from '@angular/common/http';
import { User } from '../../../interfaces/user.interface';

@Injectable({
  providedIn: 'root'
})
export class AuthService {

  private pathService = 'api/auth';

  constructor(private httpClient: HttpClient) { }

  registration(username: string, email: string, password: string) {
    console.log(username, email, password);
  }
  login(loginRequest: LoginRequest): Observable<AuthSuccess> {
    console.log(loginRequest.email, loginRequest.password);
    return this.httpClient.post<AuthSuccess>(`${this.pathService}/login`, loginRequest);
  }
  public me(): Observable<User> {
    return this.httpClient.get<User>(`${this.pathService}/me`);
  }
}
