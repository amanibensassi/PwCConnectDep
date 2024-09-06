import { Component } from '@angular/core';
import { Router } from '@angular/router';
import { CommentaireService } from 'src/app/servicess/commentaire.service';
import { InteractionService } from 'src/app/servicess/interaction.service';
import { PublicationService } from 'src/app/servicess/publication.service';

@Component({
  selector: 'app-profile',
  templateUrl: './profile.component.html',
  styleUrls: ['./profile.component.css']
})
export class ProfileComponent {
  selectedUser: any = {};
  id : any;
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
  selectedFile: File  | '' = '';


  constructor(private router : Router, private servicepub : PublicationService, private servicecomment : CommentaireService, private interactionservice : InteractionService) {
    this.getUserFromLocalStorage();
    this.getAllPublication();
  }
  onFileSelected(event: any) {
    // this.selectedFile = event.target.files[0];
    const file = event.target.files[0];
   if (file) {
     this.selectedFile = file;
     console.log('File selected:', file.name);
   } else {
     this.selectedFile = '';
     console.log('No file selected');
   }
   }

  getUserFromLocalStorage() {
    const userData = localStorage.getItem('user'); // Use 'user' as the key
    if (userData) {
      this.selectedUser = JSON.parse(userData);
      this.id = this.selectedUser.id_utilisateur;
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
    });
  
  }

  ensureCommentExists(pubId: number) {
    if (!this.newCommentaires[pubId]) {
      this.newCommentaires[pubId] = { corps: '' };
    }
  }
  getAllPublication(){
    this.servicepub.getbyUser(this.id).subscribe(data =>{ 
      console.log(data);
      this.listpub = data;
      this.listpub.forEach(
        (pub: any) => this.calculComment(pub.id));
        this.listpub.forEach((pub: any) => this.getServiceCommentByPub(pub.id));
        this.listpub.forEach((pub: any) => this.getServiceLike(pub.id));
      
      })
  }

  calculComment(id : any) { 
    this.servicecomment.calculByPub(id).subscribe(data =>{
      this.commentCounts[id] = data;})
      console.log(this.commentCounts);
  }
  getCommentCount(pubId: any) {
    this.index ++;
    return this.commentCounts[pubId] || 0;
  }
  transform(value: any[]): any[] {
    if (!value) return [];
    return value.slice().reverse();
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


likePub(pibId : number){
  const newUser = {
    id_utilisateur: this.id,}
    const newPublication = {
      id: pibId,}
  const dislike={
    publication: newPublication,
    utilisateur: newUser,
  }
this.interactionservice.addLike(dislike).subscribe((response: any) => {
this.getAllPublication();
});

}
}
