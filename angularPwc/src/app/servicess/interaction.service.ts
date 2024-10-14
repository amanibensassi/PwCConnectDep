import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';
const Base_URL = 'http://192.168.8.142:8089/PwCGather/interaction/';
@Injectable({
  providedIn: 'root'
})
export class InteractionService {

  constructor(private http: HttpClient) { }

  addLike(Like: any): Observable<any> {
    return this.http.post(Base_URL + 'add-interaction-like',Like)
  }
  addDisike(Like: any): Observable<any> {
    return this.http.post(Base_URL + 'add-interaction-dislike',Like)
  }


  calculByPub(id: any): Observable<any> {
    return this.http.get(Base_URL + 'calcul-interaction/'+id)
  }

  boolUserPub(idUSER: any,idPUB :any): Observable<any> {
    return this.http.get(Base_URL + 'interactions-by-iduser-idint/'+idPUB+'/'+idUSER)
  }
}
