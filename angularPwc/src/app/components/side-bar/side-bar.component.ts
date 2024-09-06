import { Component, Input } from '@angular/core';
import { Router } from '@angular/router';
import { ChatService } from 'src/app/servicess/chat.service';
import { NotificationService } from 'src/app/servicess/notification.service';

@Component({
  selector: 'app-side-bar',
  templateUrl: './side-bar.component.html',
  styleUrls: ['./side-bar.component.css']
})
export class SideBarComponent {
  selectedUser: any = {};
  userToModify : any;
  creatingMode : any;
  nbNotifs : any;
  listNotifs : any;
  nbChats : any;
  listChats : any;
  constructor(private router: Router, private notifService : NotificationService, private chatService : ChatService){
    this.getUserFromLocalStorage();
    this.getMyNotifs();
    this.getMyChatsNotifs();
  }
  
  getUserFromLocalStorage() {
    const userData = localStorage.getItem('user'); // Use 'user' as the key
    if (userData) {
      this.selectedUser = JSON.parse(userData);
      console.log(this.selectedUser); // Log the user object separately
    }
  }
  openModel(user : any) {
    if (user.idUser == 0) {
      this.userToModify = null;
    } else {
      this.creatingMode = false;
      this.userToModify = user;
    }
  }
  @Input() sidebarData: any;
  logout() {
    this.router.navigateByUrl('/login').finally(() => {
      location.reload();
    });
  }

  getMyNotifs(){
    this.notifService.get5ByUser(this.selectedUser.id_utilisateur).subscribe((data)=>{
      this.listNotifs = data
    })
    this.notifService.calculAllByUser(this.selectedUser.id_utilisateur).subscribe((data)=>{
      this.nbNotifs = data;
    })
  }
  getMyChatsNotifs(){
    this.notifService.calculAllChatsByUser(this.selectedUser.id_utilisateur).subscribe((data) =>{
      this.nbChats = data;
    })
    this.notifService.get5ChatsByUser(this.selectedUser.id_utilisateur).subscribe((data)=>{
      this.listChats = data;
    })
  }

  markNotifs(){
    this.notifService.getAllByUser(this.selectedUser.id_utilisateur).subscribe(data =>{
      const All =data;
      All.forEach((notif: any) =>{
        this.notifService.editNotification(notif).subscribe(data=>{
          this.nbNotifs = 0;
        })})
    })
  }

  markAsRead(notif : any){
this.notifService.editNotification(notif).subscribe(data => {
  this.getMyChatsNotifs();
  this.getMyNotifs();
  if(notif.publication != null ){
  this.router.navigateByUrl("/detail-publication/"+notif.publication.id)
}else{
  this.chatService.setSelectedUser(notif.chat.id);
  this.router.navigateByUrl('/chats');
}

}
)
  }

}
