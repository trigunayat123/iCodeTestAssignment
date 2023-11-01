import { Component } from '@angular/core';
import { Page1Service } from '../services/page1-service';
import { Page2Service } from '../services/page2.service';
import { ToastrService } from 'ngx-toastr';
import { Router } from '@angular/router';
@Component({
  selector: 'app-page2',
  templateUrl: './page2.component.html',
  styleUrls: ['./page2.component.css'],
})
export class Page2Component {
  keyValuePairs: { key: string; value: string }[] = [];
  isDiv2Visible = false;
  ngOnInit(): void {
    this.loadKeyValuePairs(); // Fetch recent notes when the component initializes
  }
  constructor( private  page1Service: Page1Service,
    private page2Service: Page2Service,
    private router:Router,
    private toastr: ToastrService){

  }
  loadKeyValuePairs(){
    this.keyValuePairs=this.page1Service.getKeyValues();
  }

  downloadTemplate() {
    this.page2Service.tempalateGenerator(this.keyValuePairs).subscribe(
      (response: Blob | unknown) => {
        if (response instanceof Blob) {
          this.downloadFile(response); 
        } else {
          console.error(response);
        }
      }
    );
  }
  
  private downloadFile(blobData: Blob) {
    const url = window.URL.createObjectURL(blobData);
    const a = document.createElement('a');
    a.href = url;
    a.download = 'template.xlsx';
    a.click();
    window.URL.revokeObjectURL(url);
  }

  uploadTemplate() {
    this.isDiv2Visible = true;
    this.page2Service.templateDataUploader(this.keyValuePairs).subscribe(
      (response) => {
        this.toastr.success("Data uploaded Successfully","",{ timeOut: 1000 });
      },
      (error) => {
        if (error && error.error) { 
          const errorMessages = Object.keys(error.error).map(key => error.error[key]);
          this.toastr.error(errorMessages+"", '', {
            timeOut: 2000,
          });
        }
      } 
    );
  }

}
 