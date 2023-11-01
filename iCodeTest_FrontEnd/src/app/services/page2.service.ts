import { Injectable } from '@angular/core';
import { HttpClient, HttpHeaders, HttpErrorResponse } from '@angular/common/http';
import { Observable, throwError } from 'rxjs';
import { catchError } from 'rxjs/operators';
@Injectable({
  providedIn: 'root'
})
export class Page2Service {
  private baseUrl  = 'http://localhost:8080/api/templates';
  constructor(private httpClient: HttpClient) { }
  tempalateGenerator(keyValuePairs : any)
  {
    const dataToSend: { [key: string]: string } = {};

    for (const item of keyValuePairs) {
    dataToSend[item.key] = item.value;
    }
    const url = `${this.baseUrl}/generateExcel`;
    // return this.httpClient.post(url, keyValuePairs);  
    const headers = new HttpHeaders({
      'Content-Type': 'application/json',
    });

    return this.httpClient.post(url, dataToSend, {
      headers,
      responseType: 'blob', // Expecting a binary response (Blob)
    }).pipe(
      catchError(this.handleError) // Handle errors
    );
  }

  private handleError(error: HttpErrorResponse) {
    console.error('An error occurred:', error);
    return throwError('An error occurred while generating the template.');
  }
  templateDataUploader(keyValuePairs : any)
  {
    console.log("Enter template data uploader");
    const dataToSend: { [key: string]: string } = {};
    for (const item of keyValuePairs) {
    dataToSend[item.key] = item.value;
    }
    const url = `${this.baseUrl}/uploadExcel`;
    return this.httpClient.post(url, dataToSend);
  }
}
