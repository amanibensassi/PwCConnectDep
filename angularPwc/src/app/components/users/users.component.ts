import { Component } from '@angular/core';
import { FormArray, FormBuilder, FormControl, FormGroup } from '@angular/forms';
import { Router } from '@angular/router';
import { ChatService } from 'src/app/servicess/chat.service';
import { UserService } from 'src/app/servicess/user.service';

@Component({
  selector: 'app-users',
  templateUrl: './users.component.html',
  styleUrls: ['./users.component.css']
})
export class UsersComponent {
  selectedUser:any;
  id : any;
  allUsers :any;
  userForm: FormGroup;
  checkedUsers: any[] = [];
constructor(private router : Router,private userservice : UserService, private fb: FormBuilder, private chatService : ChatService){
  this.userForm = this.fb.group({
    users: this.fb.array([])
  });
  this.getUserFromLocalStorage();
  this.getAllUsers();
  
}
ngOnInit() {
  this.addCheckboxes();
}


private addCheckboxes() {
  const usersFormArray = this.userForm.get('users') as FormArray;
  this.allUsers.forEach((user: any) => {
    if (user.id_utilisateur !== this.id) {
      usersFormArray.push(new FormControl(false));
    }
  });
}

onCheckboxChange(e : any, user :any) {
  if (e.target.checked) {
    this.checkedUsers.push(user);
  } else {
    const index = this.checkedUsers.findIndex(x => x.id_utilisateur === user.id_utilisateur);
    this.checkedUsers.splice(index, 1);
  }
}

  getUserFromLocalStorage() {
    const userData = localStorage.getItem('user'); 
    if (userData) {
      this.selectedUser = JSON.parse(userData);
      this.id = this.selectedUser.id_utilisateur;
      console.log(this.selectedUser); 
    }
  }

getAllUsers(){
  this.userservice.getAllUsers().subscribe((data)=> {
    this.allUsers=data;
    this.addCheckboxes();
  })
}

submit() {
  this.checkedUsers.push(this.selectedUser);

    const newChat = {
      emetteur: this.checkedUsers.map(user => ({ id_utilisateur: user.id_utilisateur }))
    }; 
   this.chatService.addChat(newChat).subscribe(
  (data) => {
    this.router.navigateByUrl('/chats');
  }
);


}

contacter() {
  this.checkedUsers.push(this.selectedUser);
  const newChat = {
    emetteur: this.checkedUsers.map(user => ({ id_utilisateur: user.id_utilisateur }))
  }; 
  const list = this.checkedUsers.map(user => {return user.id_utilisateur;})
  console.log(list);
this.chatService.ExtractChatByList(list).subscribe((data : any) =>{
const id = data;
console.log("OGHZOR HOUUUNI YEHDIKKKK §§§§§§§§§§§§§§§§§§§§§§§§§§§§§§§§§§§§");
console.log(id);

if(id==0){
this.chatService.addChat(newChat).subscribe(
  (data) => {
    console.log("SHIIIIIIIHHH");
    this.chatService.setSelectedUser(data.id)
    this.router.navigateByUrl('/chats');
  }
);
}else {
  console.log("WTF");
this.chatService.setSelectedUser(id)
 this.router.navigateByUrl('/chats');
}
})



}

}
