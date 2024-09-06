import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import { LoginComponent } from './components/login/login.component';
import { SignupComponent } from './components/signup/signup.component';
import { DashbordComponent } from './components/dashbord/dashbord.component';
import { ProfileComponent } from './components/profile/profile.component';
import { DetailsPublicationComponent } from './components/details-publication/details-publication.component';
import { ChatComponent } from './components/chat/chat.component';
import { UsersComponent } from './components/users/users.component';
import { UserCardsComponent } from './components/user-cards/user-cards.component';

const routes: Routes = [
  { path: '', component: LoginComponent , pathMatch: 'full'},
  { path: '*', component: LoginComponent , pathMatch: 'full'},
  { path: 'login', component: LoginComponent },
  { path: 'signup', component: SignupComponent },
  { path: 'dashbord', component: DashbordComponent },
  { path: 'profil', component: ProfileComponent },
  {path:'detail-publication/:id-pub', component:DetailsPublicationComponent},
  { path: 'chats', component: ChatComponent },
  { path: 'users', component: UsersComponent },
  { path: 'contactez-utilisateur', component : UserCardsComponent}
];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule { }
