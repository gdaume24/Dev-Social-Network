import { Injectable, inject } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Observable } from 'rxjs';

@Injectable({
  providedIn: 'root'
})
export class ApiService {
  private http = inject(HttpClient);
  private apiUrl = 'http://localhost:8080/users/test-request'; // Remplace par ton URL backend

  getTestRequest(): Observable<string> {
    return this.http.get(this.apiUrl, { responseType: 'text' });
  }
}