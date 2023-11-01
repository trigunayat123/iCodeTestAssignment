import { Injectable } from '@angular/core';

@Injectable({
  providedIn: 'root'
})
export class Page1Service {
  
  private keyValuePairs: { key: string; value: string }[] = [];
  constructor() { }
  getKeyValues(): { key: string; value: string }[] {
    return this.keyValuePairs;
  }
  
  setKeyValues(keyValues: { key: string; value: string }[]): void {
    this.keyValuePairs = keyValues;
  }
  
}
