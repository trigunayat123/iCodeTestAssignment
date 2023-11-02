import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';

@Injectable({
  providedIn: 'root'
})
export class Page1Service {

  private baseUrl  = 'http://localhost:8080/api/templates';
  
  private keyValuePairs: { key: string; value: string }[] = [];
  constructor(private httpClient: HttpClient) { }
  getKeyValues(): { key: string; value: string }[] {
    return this.keyValuePairs;
  }
  
  setKeyValues(keyValues: { key: string; value: string }[]): void {
    this.keyValuePairs = keyValues;
  }

  getTemplateMappings()
  {
    const url = `${this.baseUrl}/mappings`;
    return this.httpClient.get(url);
  }
  
}
