import { HttpClient, HttpHeaders, HttpParams } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { BehaviorSubject, Observable } from 'rxjs';
const Base_URL = 'http://localhost:8089/PwCGather/chat/';
@Injectable({
  providedIn: 'root'
})

export class ChatService {
  private selectedUserSubject: BehaviorSubject<any> = new BehaviorSubject<any>(null);
  constructor(private http: HttpClient) { }

  editChat(id: any): Observable<any> {
    return this.http.put(Base_URL + 'edit-chat/',id)
  }
  getAllByUser(id: any): Observable<any> {
    return this.http.get(Base_URL + 'chatsByIdUser/'+id)
  }

  addChat(object : any): Observable<any> {
    return this.http.post(Base_URL + 'add-chat',object)
  }
  ExtractChatByList(object : any): Observable<any> {
    return this.http.post<any>(Base_URL + 'chatsByListId',object)
  }

 
  setSelectedUser(user: any): void {
    this.selectedUserSubject.next(user);
  }
  getSelectedUser() {
    return this.selectedUserSubject.asObservable();
  }
 
  getSelectedUserValue(): any | null {
    return this.selectedUserSubject.getValue();
  }
 
}
