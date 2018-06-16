import { Injectable } from '@angular/core';
import { HttpClient , HttpClientModule , HttpEvent, HttpInterceptor, HttpHandler, HttpHeaders ,HttpResponse, HttpRequest, HttpParams} from '@angular/common/http';
import { RegisteredUrl } from '../registered-url';
import 'rxjs/add/operator/map';
import { catchError, retry } from 'rxjs/operators';
import { Observable, throwError } from 'rxjs';

import { ShortenedUrlComponent } from '../components/shortened-url/shortened-url.component';
import { Response } from '@angular/http';

const httpOptions = {
  headers: new HttpHeaders({
    'Content-Type':  'application/json',
    'Authorization': 'my-auth-token'
  })
};

@Injectable()
export class UrlShortenerService {

  private baseUrl:string = 'http://localhost/8080';
  

  constructor(private _http:HttpClient) { }

  createShortenedURL(){

    console.log("inside service createShortenedURL------------");
    const req = new HttpRequest('POST', this.baseUrl, null, {
      reportProgress: true,
      responseType: 'text',
      params: new HttpParams().set('url', 'https://www.udemy.com/courses/it-and-software/')
    });
    return this._http.request(req);
  }
  
}
