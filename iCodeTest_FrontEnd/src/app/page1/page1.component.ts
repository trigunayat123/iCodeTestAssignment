import { Component } from '@angular/core';
import { Router } from '@angular/router';
import { Page1Service } from '../services/page1-service';
import { Toast, ToastrService } from 'ngx-toastr';
@Component({
  selector: 'app-page1',
  templateUrl: './page1.component.html',
  styleUrls: ['./page1.component.css'],
})  
export class Page1Component {
  constructor(private toastr: ToastrService,
     private router: Router,
     private  page1Service: Page1Service) {}

     ngOnInit(): void {
      this.loadtemplateMappings(); // Fetch recent notes when the component initializes
    }   

  keyValuePairs: { key: string; value: string }[] = [];

  addKeyValuePair() {
    this.keyValuePairs.push({ key: '', value: '' });
  }

  loadtemplateMappings() {
    this.page1Service.getTemplateMappings().subscribe(
      (response: any) => {
        this.keyValuePairs = Object.entries(response).map(([key, value]) => ({ key, value: value as string }));
        console.log("Key-Value Pairs", this.keyValuePairs);
        console.log("Hiii");
      },
      (error) => {
        this.toastr.error("Some Error Occurred !!", "", { timeOut: 2000 });
      }
    );
  }
  

  removeKeyValuePair(index: number) {
    this.keyValuePairs.splice(index, 1);
  }

  navigateToPage2() {
    if(this.keyValuePairs.length==0){
    this.toastr.error('Please define some fields and their values !!', '', {
      timeOut: 2000,
    });
    return;
  }
    for (const pair of this.keyValuePairs) {
      if (pair.key === '' || pair.value === '') {
        this.toastr.error('Please fill all the fields and values !!', '', {
          timeOut: 2000,
        });
        return;
      }
    }
    this.page1Service.setKeyValues(this.keyValuePairs);
    this.router.navigate(['/page2']);
  }
}
