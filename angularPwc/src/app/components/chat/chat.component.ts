import { Component } from '@angular/core';
import { Router } from '@angular/router';
import { ChatService } from 'src/app/servicess/chat.service';
import { MessageService } from 'src/app/servicess/message.service';
import { NotificationService } from 'src/app/servicess/notification.service';
import { WebSocketService } from 'src/app/servicess/web-socket.service';

@Component({
  selector: 'app-chat',
  templateUrl: './chat.component.html',
  styleUrls: ['./chat.component.css']
})
export class ChatComponent {
  selectedUser : any;
  id : any;
  listChats : any;
  listOfMessages : any;
  selectedId : any;
  msg : any;
  LastMsgList: { [chatId: number]: any } = {};
  constructor(private router : Router,
    private chatservice : ChatService,
    private messageservice : MessageService,
    private notifService : NotificationService,
    private webSocket : WebSocketService){
    this.getUserFromLocalStorage() ;
    
    

  }


  ngOnInit () : void{
  //  this.webSocket.joinRoom("ABC");
  }
  getUserFromLocalStorage() {
    const userData = localStorage.getItem('user'); 
    if (userData) {
      this.selectedUser = JSON.parse(userData);
      this.id = this.selectedUser.id_utilisateur;
      this.getAllChats();
    }
  }
  transform(value: any[]): any[] {
    if (!value) return [];
    return value.slice().reverse();
  }


  lastItem(value: any[]): any[] {
    if (!value) return [];
    return value.slice().reverse();
  }
  getAllChats(){
    this.chatservice.getAllByUser(this.id).subscribe(
      (data)=>{
        this.listChats = data;
        this.listChats.forEach((chat: any) => this.getLastMessage(chat.id));
        if(this.listChats.length > 0)
       {
        this.chatservice.getSelectedUser().subscribe( (user: any) => 
          { this.selectedId = user ? user : null;}
        )
        if(this.selectedId!=null){
       // this.selectedId = this.listChats[0].id;
        this.getCurrentConverstation(this.selectedId);
        }
         
       }
      }
    )
  }


  onChatClick(id : any){
    this.selectedId = id;
  }

  getLastMessage(id : any){
this.messageservice.getLastMessageAllByUser(id).subscribe((data:any)=>{
  this.LastMsgList[id]=data;
})
  }

  getCurrentConverstation(id : any){
  //  console.log(id);
   this.messageservice.getAllByUser(id).subscribe(
  (data)=>{
    this.listOfMessages = data;
  //  console.log(this.listOfMessages);
    this.selectedId = id;
  }
)
  }

discard(){
  this.msg = '';
}

detectChanges(){
  if ((this.msg=!'')&& (this.msg!=" ")&&(this.selectedUser.id_utilisateur!=this.LastMsgList[this.selectedId].emetteur.id_utilisateur)){

    this.transform(this.listOfMessages).forEach((msg: any) => {
      this.messageservice.editMessage(msg).subscribe (data => {
        console.log("successful vu");
        console.log(msg);
      })
    })
      
    }



 
}




  addmessage(){
    //console.log(this.selectedId);
    //console.log(this.msg);
    if((this.msg!='')||(this.msg!=" " )){
    const newMessage = {
      text : this.msg,
      chat : {id : this.selectedId},
      emetteur : {id_utilisateur : this.id}
    }
    this.messageservice.addChat(newMessage).subscribe(
     data =>{
      this.getCurrentConverstation(this.selectedId);
    this.msg = '';
      // creation taa notif mteena ::  userReceiver : this.pub.utilisateur.id_utilisateur, ==> chkoun bech yekhou notif
    const newNotif ={
      type:"Message",
      sender :{
         id_utilisateur: this.id
      },
      chat :{id : this.selectedId},
     contenu : this.msg
 }
 this.notifService.addNotification(newNotif).subscribe(
  (data) => {console.log('The notification was added successfully'+ data);})
 //this.webSocket.sendMessage("ABC", newNotif);
 //this.webSocket.getMessageSubject().subscribe(data => {
//  console.log("AAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAA");
  console.log(data);
 //})
 
 //this.send(newNotif);
    }
    )
  } else {alert("Veuillez remplirele champs textuel !")}
}
}