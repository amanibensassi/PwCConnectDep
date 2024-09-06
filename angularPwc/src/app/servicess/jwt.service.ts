import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';
const Base_URL = 'http://localhost:8089/PwCGather/';
@Injectable({
  providedIn: 'root'
})
export class JwtService {

  constructor(private http: HttpClient) { }

  login(loginRequest: any): Observable<any> {
    return this.http.post(Base_URL + 'login', loginRequest)
  }
  register(signRequest: any): Observable<any> {
    return this.http.post(Base_URL + 'signup', signRequest)
  }
  logout(): void {
    localStorage.removeItem('jwtToken');
  }
}
