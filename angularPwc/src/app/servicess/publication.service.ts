import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';

const Base_URL = 'http://192.168.8.142:8089/PwCGather/publication';
@Injectable({
  providedIn: 'root'
})
export class PublicationService {

  constructor(private http: HttpClient) { }
  getbyUser(id: any): Observable<any> {
    return this.http.get(Base_URL + '/get-by-user/'+ id)
  }
  getAll(): Observable<any> {
    return this.http.get(Base_URL + '/publications')
  }
  getPubById(id : any): Observable<any> {
    return this.http.get(Base_URL + '/get-by-idpub/'+id)
  }
  addPubNoPicture(object : any): Observable<any> {
    return this.http.post(Base_URL + '/add-pub',object)
  }

  addPub(object : any): Observable<any> {
    return this.http.post(Base_URL,object)
  }
}
