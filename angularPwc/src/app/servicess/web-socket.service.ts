import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Client, Stomp } from '@stomp/stompjs';
import { BehaviorSubject } from 'rxjs';
import * as SockJS from 'sockjs-client';
@Injectable({
  providedIn: 'root'
})
export class WebSocketService {

  private stompClient: any;
 private msgSubject : BehaviorSubject<any> = new BehaviorSubject<any>([]);

  constructor(private http: HttpClient) { 
    this.initConnectionSocket();
  }
  
  
  
  initConnectionSocket(){
    const url = '//192.168.8.142:8089/PwCGather/websocket';
    const socket = new SockJS(url);
    this.stompClient = Stomp.over(socket);
  }
  
  
  
  joinRoom(roomId:any){
    const url1 = `/PwCGather/topic/${roomId}`; 
    this.stompClient.connect({}, ()=>{
    console.log("socket isconnected");
      console.log(url1  );
      this.stompClient.subscribe(url1, (messages : any)=>{
        const messageContent = JSON.parse(messages.body);
        console.log("Connected successfully : msg written by me mech default !!");
        console.log(messageContent);
        this.msgSubject.next([messageContent]);
      })
    });

  
  }

  sendMessage(roomId : any, chat: any){
    const url1 = `/PwCGather/app/chat/`; 
   // this.stompClient.send(url1+roomId,{},JSON.stringify(chat));
    this.stompClient.send('/PwCGather/app/chat/' + roomId, {}, JSON.stringify(chat));
  }

  getMessageSubject(){
    console.log(this.getMessageSubject);
    return this.msgSubject;
  }
}
