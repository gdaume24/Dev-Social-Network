import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';

@Injectable({
  providedIn: 'root'
})
export class UserService {
  private apiUrl = 'api/users';

  constructor(private httpClient: HttpClient) { }

  updateUser(user: any): Observable<any> {
    return this.httpClient.put(`${this.apiUrl}`, user);
  }
}
