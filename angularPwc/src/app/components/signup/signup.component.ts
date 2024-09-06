import { Component } from '@angular/core';
import { FormBuilder, FormGroup, Validators } from '@angular/forms';
import { Router } from '@angular/router';
import { JwtService } from 'src/app/servicess/jwt.service';

@Component({
  selector: 'app-signup',
  templateUrl: './signup.component.html',
  styleUrls: ['./signup.component.css']
})
export class SignupComponent {
email : any;
username : any;
surname: any;
password : any;
grad : any;
tel : any;
adresse : any;
registerForm: FormGroup;
selectedFile!: File ;
constructor(private router : Router, private serviceuser : JwtService,  private fb: FormBuilder) {
  localStorage.removeItem("jwtToken");
  localStorage.removeItem("user");
  this.registerForm = this.fb.group({
    prenom: ['', [Validators.required]],
    nom: ['', [Validators.required]],
    email: ['', [Validators.required]],
    telephone: ['', [Validators.required]],
    grad: ['', [Validators.required]],
    adresse: ['', [Validators.required]],
    password: ['', [Validators.required]], 
    details: ['', [Validators.required]], 
  });
}
onFileSelected(event: any) {
  this.selectedFile = event.target.files[0];
}

submitForm() {
  if (this.registerForm.valid) {
    const formData = new FormData();
    formData.append('prenom', this.registerForm.get('prenom')?.value);
    formData.append('nom', this.registerForm.get('nom')?.value);
    formData.append('email', this.registerForm.get('email')?.value);
    formData.append('telephone', this.registerForm.get('telephone')?.value);
    formData.append('adresse', this.registerForm.get('adresse')?.value);
    formData.append('password', this.registerForm.get('password')?.value);
    formData.append('grad', this.registerForm.get('grad')?.value);
    formData.append('details', this.registerForm.get('details')?.value);
    formData.append('picture',this.selectedFile);

const newUser = new FormData();
 /*newUser={
  nom : this.registerForm.get('nom')?.value,
  prenom : this.registerForm.get('prenom')?.value,
  email  : this.registerForm.get('email')?.value,
  telephone : this.registerForm.get('telephone')?.value,
  grad : this.registerForm.get('grad')?.value,
  adresse : this.registerForm.get('adresse')?.value,
  password : this.registerForm.get('password')?.value,
  picture : this.selectedFile
}*/
newUser.append('prenom', this.registerForm.get('prenom')?.value);
newUser.append('nom', this.registerForm.get('nom')?.value);
newUser.append('email', this.registerForm.get('email')?.value);
newUser.append('telephone', this.registerForm.get('telephone')?.value);
newUser.append('adresse', this.registerForm.get('adresse')?.value);
newUser.append('password', this.registerForm.get('password')?.value);
newUser.append('grad', this.registerForm.get('grad')?.value);
newUser.append('details', this.registerForm.get('details')?.value);
newUser.append('picture',this.selectedFile);
console.log('FormData entries:');
newUser.forEach((value, key) => {
  console.log(key + ': ' + value);
});
console.log(newUser);
    this.serviceuser.register(newUser).subscribe(
      (response) => {
        //alert("User Added Successfully");
        console.log(response);
        this.router.navigate(['/login']);
      },
      (error) => {
        console.error('Error registering user:', error);
        // Gérer l'erreur, par exemple afficher un message d'erreur à l'utilisateur
      }
    );
  } else {
    alert('Please fill out all required fields and select an image.');
  }
}





addUser(){
  const newUser={
    nom : this.surname,
    prenom : this.username,
    email  : this.email,
    telephone : this.tel,
    grad : this.grad,
    adresse : this.adresse,
    password : this.password
  }
  console.log(newUser);
  this.serviceuser.register(newUser).subscribe(
    (response) => {
      //
      
  alert("User Added Successfully");
      console.log(response);
      this.router.navigate(['/login']);
    },
    (error) => {
      console.error('Error registering user:', error);
      // Gérer l'erreur, par exemple afficher un message d'erreur à l'utilisateur
    }
  );

}
routing(){
  this.router.navigateByUrl('/login');
}

}