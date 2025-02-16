import { Injectable } from '@angular/core';

@Injectable({
  providedIn: 'root'
})
export class AuthService {

  constructor() { }

  registration(username: string, email: string, password: string) {
    console.log(username, email, password);
  }
  connection(username: string, password: string) {
    console.log(username, password);
  }
}
