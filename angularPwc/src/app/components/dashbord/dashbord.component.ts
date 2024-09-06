import { Component } from '@angular/core';
import { Router } from '@angular/router';
import { forkJoin } from 'rxjs';
import { CommentaireService } from 'src/app/servicess/commentaire.service';
import { InteractionService } from 'src/app/servicess/interaction.service';
import { NotificationService } from 'src/app/servicess/notification.service';
import { PublicationService } from 'src/app/servicess/publication.service';

@Component({
  selector: 'app-dashbord',
  templateUrl: './dashbord.component.html',
  styleUrls: ['./dashbord.component.css']
})
export class DashbordComponent {
  selectedUser: any = {};
  NotifPub : any;
  listpub : any;
  number : any;
  index : number=-1;
  commentCounts: { [pubId: number]: number } = {};
  likeCounts: {[pubId:number]:number} = {};
  commentList: { [pubId: number]: any } = {};
  boolLike: { [pubId: number]: any } = {};
  newCommentaires: { [key: number]: { corps: string } } = {};
  bodyPub : any;
  newCommentaire: any = { corps: '' }; 
  id : any;
  selectedFile: File  | '' = '';
  pub : any;

  constructor(private router : Router, 
    private servicepub : PublicationService,
     private servicecomment : CommentaireService, 
     private interactionservice : InteractionService,
    private notifService : NotificationService) {
    this.getUserFromLocalStorage();
    this.getAllPublication();
  }
  onFileSelected(event: any) {
   // this.selectedFile = event.target.files[0];
   const file = event.target.files[0];
  if (file) {
    this.selectedFile = file;
  } else {
    this.selectedFile = '';
  }
  }
addPublication(){
  const formData = new FormData();
  formData.append('corps', this.bodyPub);
  formData.append('id-user', this.id);
if(this.selectedFile!=''){
  formData.append('picture', this.selectedFile);
  this.servicepub.addPub(formData).subscribe((response: any) =>{
    this.getAllPublication();
    this.bodyPub="";
    this.selectedFile = "";
    
  });
}else{
  this.servicepub.addPubNoPicture(formData).subscribe((response: any) =>{
    this.getAllPublication();
    this.bodyPub="";
    this.selectedFile = '';
    
  });
}


 /* const newUser = {
    id_utilisateur: this.id,}
  const newPublication = {
    corps: this.bodyPub,
    utilisateur:newUser,
  }*/
 
}

addcomment(id : any){
  this.ensureCommentExists(id);
  const newUser = {
    id_utilisateur: this.id,}
    const newPublication = {
      id: id,}
  const newCommentaire = {
    corps: this.newCommentaires[id],
    publication: newPublication,
    utilisateur: newUser,
  };


  this.servicecomment
  .addCom(newCommentaire)
  .subscribe((response: any) => {
    this.newCommentaires={};
    this.getAllPublication();
    /// notification : nekhthou pub kemla :
    this.servicepub.getPubById(id).subscribe((data)=>{
      this.pub =data;})
      // creation taa notif mteena ::  userReceiver : this.pub.utilisateur.id_utilisateur, ==> chkoun bech yekhou notif
      this.MakeCommentNotif(id);

  });

}

ensureCommentExists(pubId: number) {
  if (!this.newCommentaires[pubId]) {
    this.newCommentaires[pubId] = { corps: '' };
  }
}

  getUserFromLocalStorage() {
    const userData = localStorage.getItem('user'); // Use 'user' as the key
    if (userData) {
      this.selectedUser = JSON.parse(userData);
      this.id = this.selectedUser.id_utilisateur;
      console.log(this.selectedUser); // Log the user object separately
    }
  }

  getAllPublication() { // Use 'user' as the key
    this.servicepub.getAll().subscribe(data =>{ 
      this.listpub = data;
      this.listpub.forEach((pub: any) => this.calculComment(pub.id));
      this.listpub.forEach((pub: any) => this.getServiceCommentByPub(pub.id));
      this.listpub.forEach((pub: any) => this.getServiceLike(pub.id));
      })
  }


  transform(value: any[]): any[] {
    if (!value) return [];
    return value.slice().reverse();
  }

  calculComment(id : any) { 
    this.servicecomment.calculByPub(id).subscribe(data =>{
      this.commentCounts[id] = data;})
  }
  getCommentCount(pubId: any) {
    return this.commentCounts[pubId] || 0;
  }

  getServiceCommentByPub(id: any){
    this.servicecomment.getByPub(id).subscribe(data =>{
      this.commentList[id] = data;})
  }
getServiceLike(id: any){
  this.interactionservice.calculByPub(id).subscribe(data =>{
    this.likeCounts[id] = data;})

    this.interactionservice.boolUserPub(id,this.id ).subscribe(data =>{
      this.boolLike[id] = data;
    
    })
 }


  hasComments(pubId: number): boolean {
    const comments = this.commentList[pubId];
    return comments && comments.length > 0;
  }



  dislikePub(pibId : number){
    const newUser = {
      id_utilisateur: this.id,}
      const newPublication = {
        id: pibId,}
    const dislike={
      publication: newPublication,
      utilisateur: newUser,
    }
this.interactionservice.addDisike(dislike).subscribe((response: any) => {
  this.getAllPublication();
});

}

MakeCommentNotif(id : any){
  this.servicepub.getPubById(id).subscribe(data => {
    this.NotifPub=data;
    if(this.NotifPub.utilisateur.id_utilisateur != this.selectedUser.id_utilisateur){
      const newNotif ={
        type:"commentaire",
        sender :{
           id_utilisateur: this.id
        },
        publication : this.pub,
       contenu : this.selectedUser.prenom+" "+ this.selectedUser.nom + " a commenté sur votre poste"
   }
   this.notifService.addNotification(newNotif).subscribe(
    (data) => {console.log('The notification was added successfully'+ data);}
   )}
  })
}

getPubById(id : any){
  this.servicepub.getPubById(id).subscribe(data => {
    this.NotifPub=data;
    if(this.NotifPub.utilisateur.id_utilisateur != this.selectedUser.id_utilisateur){
    const newNotif ={
         type:"Like",
         sender :{
            id_utilisateur: this.id
        },
        publication : this.pub,
        contenu : ""
    }
    this.notifService.addNotification(newNotif).subscribe(
     (data) => {console.log('The notification was added successfully');});
    }
  })
    
  
}

likePub(pibId : number){
  const newUser = {
    id_utilisateur: this.id,}
    const newPublication = {
      id: pibId,}
  const dislike={
    publication: newPublication,
    utilisateur: newUser,
  }
  
  this.servicepub.getPubById(pibId).subscribe((data)=>{
   this.pub =data;
   
    })
this.interactionservice.addLike(dislike).subscribe((response: any) => {
this.getAllPublication();
this.getPubById(pibId);


});


}


}
