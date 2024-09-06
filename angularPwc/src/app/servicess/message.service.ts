import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';
const Base_URL = 'http://localhost:8089/PwCGather/message';
@Injectable({
  providedIn: 'root'
})
export class MessageService {

  constructor(private http: HttpClient) { }


  editMessage(id: any): Observable<any> {
    return this.http.put(Base_URL + '/update-message',id)
  }
  getAllByUser(id: any): Observable<any> {
    return this.http.get(Base_URL + '/get-by-idChat/'+id)
  }
  getLastMessageAllByUser(id: any): Observable<any> {
    return this.http.get(Base_URL + '/getLastMessageByChatId/'+id)
  }

  addChat(object : any): Observable<any> {
    return this.http.post(Base_URL + '/add-message',object)
  }
}
