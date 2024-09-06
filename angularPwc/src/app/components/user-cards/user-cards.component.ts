import { Component } from '@angular/core';
import { FormBuilder } from '@angular/forms';
import { Router } from '@angular/router';
import { ChatService } from 'src/app/servicess/chat.service';
import { UserService } from 'src/app/servicess/user.service';

@Component({
  selector: 'app-user-cards',
  templateUrl: './user-cards.component.html',
  styleUrls: ['./user-cards.component.css']
})
export class UserCardsComponent {
  allUsers: any = [];
  filteredUsers: any = [];
  selectedUser: any;
  searchTerm: string = '';

  constructor(private router: Router, private userservice: UserService, private fb: FormBuilder, private chatService: ChatService) {
    
    this.getUserFromLocalStorage();
  }

  getAllUsers() {
    this.userservice.getAllUsers().subscribe((data) => {
      
      this.allUsers = data;
      if (this.selectedUser) {
        this.allUsers = this.allUsers.filter((user: any) => user.id_utilisateur !== this.selectedUser.id_utilisateur);
     
      }
      this.filteredUsers = [...this.allUsers];
    });
  }

  getUserFromLocalStorage() {
    const userData = localStorage.getItem('user');
    if (userData) {
      this.selectedUser = JSON.parse(userData);
    }
    this.getAllUsers();
  }

  filterUsers() {
    this.filteredUsers = this.allUsers.filter((user: any) => {
      const searchStr = this.searchTerm.toLowerCase().split('');
  
      const prenomMatches = searchStr.every(char => user.prenom.toLowerCase().includes(char));
      const nomMatches = searchStr.every(char => user.nom.toLowerCase().includes(char));
  
      return prenomMatches || nomMatches;
    });
  }
  

  contacter(user: any) {
    const newChat = {
      emetteur: [
        { id_utilisateur: user.id_utilisateur },
        { id_utilisateur: this.selectedUser.id_utilisateur }
      ]
    };
    const list = [user.id_utilisateur, this.selectedUser.id_utilisateur];
    console.log(list);
    this.chatService.ExtractChatByList(list).subscribe((data: any) => {
      const id = data;
      if (id == 0) {
        this.chatService.addChat(newChat).subscribe(
          (data) => {
            this.chatService.setSelectedUser(data.id);
            this.router.navigateByUrl('/chats');
          }
        );
      } else {
        this.chatService.setSelectedUser(id);
        this.router.navigateByUrl('/chats');
      }
    });
  }
}
