import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';

const Base_URL = 'http://localhost:8089/PwCGather/utilisateur';
@Injectable({
  providedIn: 'root'
})

export class UserService {
 

  constructor(private http: HttpClient) {}



  retrieveByMail(mail: any) {
    return this.http.get(`${Base_URL}/retrieve-user-by-mail/${mail}`);
  }
  getAllUsers(){
    return this.http.get(`${Base_URL}/users`);
  }


}
