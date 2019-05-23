import { Injectable } from '@angular/core';
import { HttpClient,HttpHeaders, HttpErrorResponse } from '@angular/common/http';
import {Hostdetails} from './hostdetails';
import { map, catchError } from 'rxjs/operators';
import { throwError } from 'rxjs';

@Injectable({
  providedIn: 'root'
})
export class RestService {

  constructor(private http: HttpClient) { }

  serverUrl: string = 'https://a9e7811275c6b11e993d20afcba7175d-1705791662.us-east-1.elb.amazonaws.com/spring/hostdetailsSpringApp';

   // Rest Items Service: Read all REST Items
   GetRestItems() {
    return this.http.get<Hostdetails[]>(this.serverUrl)
      .pipe(map(data => data));
  }
    
}
