import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';
const Base_URL = 'http://192.168.8.142:8089/PwCGather/notification';
@Injectable({
  providedIn: 'root'
})
export class NotificationService {

  constructor(private http: HttpClient) { }


  editNotification(id: any): Observable<any> {
    return this.http.put(Base_URL + '/Modify-notification',id)
  }
  getAllByUser(id: any): Observable<any> {
    return this.http.get(Base_URL + '/notification-by-user/'+id)
  }

  calculAllByUser(id: any): Observable<any> {
    return this.http.get(Base_URL + '/Calcul-notification/'+id)
  }
  getAllChatsByUser(id: any): Observable<any> {
    return this.http.get(Base_URL + '/notificationChat-by-user/'+id)
  }
  get5ByUser(id: any): Observable<any> {
    return this.http.get(Base_URL + '/5notification-by-user/'+id)
  }
  get5ChatsByUser(id: any): Observable<any> {
    return this.http.get(Base_URL + '/5notificationChat-by-user/'+id)
  }

  calculAllChatsByUser(id: any): Observable<any> {
    return this.http.get(Base_URL + '/CalculChat-notification/'+id)
  }

  addNotification(object : any): Observable<any> {
    return this.http.post(Base_URL + '/add-notification',object)
  }



}
