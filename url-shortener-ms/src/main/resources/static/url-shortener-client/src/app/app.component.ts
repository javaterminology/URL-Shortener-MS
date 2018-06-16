import { Component } from '@angular/core';
import { HttpClient, HttpParams,HttpHeaders } from '@angular/common/http';
import { Observable } from 'rxjs/Observable';
import { ShortenedURL } from './shortenedurl';
import 'rxjs/add/operator/map';
import 'rxjs/add/operator/catch';
import 'rxjs/add/operator/retry';



@Component({
  selector: 'app-root',
  templateUrl: './app.component.html',
  styleUrls: ['./app.component.css']
})

export class AppComponent {

    readonly POST_URL = "api/urlshortener";

    readonly GET_URL = "https://jsonplaceholder.typicode.com/posts";

    //createPost: Observable<ShortenedURL[]>;
    createPost: Observable<any>;

    posts: Observable<ShortenedURL[]>;

    constructor(private _http : HttpClient){}

    createShortURL(){
      console.log("<<< inside createShort URL >>>"+this.POST_URL);
      let params:HttpParams = new HttpParams();

      params.set('url','https://dzone.com/articles/microservices-with-spring-boot-part-4-using-ribbon');
      
      this.createPost = this._http.post<any[]>(this.POST_URL+'/post',{params});
    }

    getPosts(){
      console.log("<<< inside getPosts >>>");
      let params:HttpParams = new HttpParams();
      let headers: HttpHeaders  = new HttpHeaders();
      headers.set('Authorization','auth-token');
      params.set('userId','1');
      this.posts = this._http.get<ShortenedURL[]>(this.GET_URL,{headers});
    }

}
