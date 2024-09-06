import { Component, OnInit } from '@angular/core';
import { NavigationEnd, Router } from '@angular/router';

@Component({
  selector: 'app-root',
  templateUrl: './app.component.html',
  styleUrls: ['./app.component.css']
})
export class AppComponent implements OnInit {
  showSidebar: boolean = false;
  intervalId: any;
  constructor(private router: Router) {
  
  }

  ngOnInit(): void {
   
      this.getUserFromLocalStorage();
   
  }
      
     
    
  
  


  getUserFromLocalStorage() {
    const userData = localStorage.getItem('user'); 
    if (userData) {
      
      this.showSidebar = true
    }
  }

  
}
