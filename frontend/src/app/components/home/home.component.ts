import { Component, OnInit } from '@angular/core';
import { Router } from '@angular/router';
import { LogService } from './../../service/log.service';

@Component({
  selector: 'app-home',
  templateUrl: './home.component.html',
  styleUrls: ['./home.component.css']
})
export class HomeComponent implements OnInit {
  

  constructor(private router:Router) { 
    
  }

  ngOnInit(): void {
  }

  //After clicking the "Book Your Food" it move to products page.

  viewourfood(){
    // this.logService.sendId('')                                 
    this.router.navigate(['/products']);
  }

}
