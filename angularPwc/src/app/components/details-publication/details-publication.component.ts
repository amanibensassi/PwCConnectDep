import { Component } from '@angular/core';
import { ActivatedRoute, Router } from '@angular/router';
import { CommentaireService } from 'src/app/servicess/commentaire.service';
import { InteractionService } from 'src/app/servicess/interaction.service';
import { NotificationService } from 'src/app/servicess/notification.service';
import { PublicationService } from 'src/app/servicess/publication.service';

@Component({
  selector: 'app-details-publication',
  templateUrl: './details-publication.component.html',
  styleUrls: ['./details-publication.component.css']
})
export class DetailsPublicationComponent {
  idpub : any;
  pub : any;
  Comments : any;
  nbLove : any;
  nbCom : any;
  body:any;
  id :any;
  boolLike :any;
  selectedUser: any = {};
constructor( private router: Router,
  private notifService : NotificationService,
  private route: ActivatedRoute,private pubService : PublicationService,private commentService:CommentaireService,private interactionService : InteractionService){

}

  ngOnInit(): void {
    this.idpub = this.route.snapshot.paramMap.get('id-pub');
    this.getPublication();
    this.getUserFromLocalStorage();
    this.getMyCommentList();
    this.calculInteractions();
    this.getCommentCount()
  }
  getCommentCount(){
    this.commentService.calculByPub(this.idpub).subscribe((data)=>{this.nbCom=data;
      console.log(data)
    })
    }
    transform(value: any[]): any[] {
      if (!value) return [];
      return value.slice().reverse();
    }
  getPublication(){
    this.pubService.getPubById(this.idpub).subscribe((data)=>{
    this.pub=data;
   
    })
  }
  getMyCommentList(){
this.commentService.getAllByPub(this.idpub).subscribe((data)=>{
  this.Comments=data;
});}

calculInteractions(){
this.interactionService.calculByPub(this.idpub).subscribe((data)=>{
  this.nbLove=data;
});
this.interactionService.boolUserPub(this.idpub,this.id ).subscribe(data =>{
  this.boolLike = data;

})

}
MakeCommentNotif(){
  
   
    if(this.pub.utilisateur.id_utilisateur != this.selectedUser.id_utilisateur){
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
this.interactionService.addDisike(dislike).subscribe((response: any) => {
  this.calculInteractions();
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
this.interactionService.addLike(dislike).subscribe((response: any) => {
  this.calculInteractions();
});

}

addcomment(id : any){
  const newUser = {
    id_utilisateur: this.id,}
    const newPublication = {
      id: id,}
  const newCommentaire = {
    corps: this.body,
    publication: newPublication,
    utilisateur: newUser,
  };


  this.commentService
  .addCom(newCommentaire)
  .subscribe((response: any) => {
    this.body = '';
    this.getCommentCount();
   this. getMyCommentList()
   this.MakeCommentNotif();
  });

}

getUserFromLocalStorage() {
  const userData = localStorage.getItem('user'); 
  if (userData) {
    this.selectedUser = JSON.parse(userData);
    this.id = JSON.parse(userData).id_utilisateur;
  }
}


  }

