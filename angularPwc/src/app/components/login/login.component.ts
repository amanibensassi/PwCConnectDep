import { Component } from '@angular/core';
import { FormBuilder, FormGroup, Validators } from '@angular/forms';
import { Router } from '@angular/router';
import { JwtService } from 'src/app/servicess/jwt.service';
import { UserService } from 'src/app/servicess/user.service';

@Component({
  selector: 'app-login',
  templateUrl: './login.component.html',
  styleUrls: ['./login.component.css']
})
export class LoginComponent {
  loginForm!: FormGroup;
  user: any;

  constructor(
    private service: JwtService,
    private fb: FormBuilder,
    private router: Router,
    private userService: UserService
  ) {}
  ngOnInit(): void {
    localStorage.removeItem("jwtToken");
    localStorage.removeItem("user");
    this.loginForm = this.fb.group({
      email: ['', [Validators.required, Validators.email]],
      password: ['', Validators.required]
    });
  }

  submitFrom() {
    
    
    this.service.login(this.loginForm.value).subscribe(
      (response) => {
        console.log(response);
        if (response.jwtToken !== null) {
         // alert("Welcome To PwCGather ");
          const jwtToken = response.jwtToken;
          localStorage.setItem('jwtToken', jwtToken);
  
          this.userService.retrieveByMail(this.loginForm.get('email')?.value).subscribe(data => {
            this.user = data;
            console.log(this.user);
            localStorage.setItem('user', JSON.stringify(data));
            this.router.navigateByUrl('/dashbord').finally(() => {
              location.reload();
            });
          });
        }
      },
      (error) => {
        // Handle error
        console.error("Error:", error);
        // Alert the user about the error
        alert("Incorrect username or password.");
      }
    );
  }
}
