import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';
const Base_URL = 'http://192.168.8.142:8089/PwCGather/commentaire/';
@Injectable({
  providedIn: 'root'
})
export class CommentaireService {

  constructor(private http: HttpClient) { }
  calculByPub(id: any): Observable<any> {
    return this.http.get(Base_URL + 'calcul-commentaire/'+id)
  }
  getByPub(id: any): Observable<any> {
    return this.http.get(Base_URL + 'get3-by-publication/'+id)
  }
  getAllByPub(id: any): Observable<any> {
    return this.http.get(Base_URL + 'get-by-publication/'+id)
  }

  addCom(object : any): Observable<any> {
    return this.http.post(Base_URL + 'add-commentaire',object)
  }
}
