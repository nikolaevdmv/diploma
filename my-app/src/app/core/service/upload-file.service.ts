import {Injectable} from '@angular/core';
import {HttpClient, HttpRequest, HttpEvent} from '@angular/common/http';
import {Observable} from 'rxjs/Observable';
import * as jwt_decode from 'jwt-decode';
import {ResponseContentType} from '@angular/http';

@Injectable()
export class UploadFileService {

  constructor(private http: HttpClient) {
  }

  pushFileToStorage(file: File, conference_id: number): Observable<HttpEvent<{}>> {
    const formdata: FormData = new FormData();


    formdata.append('file', file);
    console.log(formdata);


    const req = new HttpRequest('POST', '/api/conferences/' + conference_id + '/submission-list', formdata, {
      reportProgress: true,
      responseType: 'text'
    });

    return this.http.request(req);
  }

  getFiles() {
    return this.http.get('/api/submission-list');
  }

  getDecodedAccessToken(): any {
    const token = localStorage.getItem('token');
    try {
      return jwt_decode(token);
    } catch (Error) {
      return null;
    }
  }


  downloadFile(submissionId: number, conferenceId: number, documentId: number) {
    return this.http.get('/api/conferences/' + conferenceId + '/submissions/' + submissionId + '/documents/' + documentId,
      {responseType: 'blob', observe: 'response'});
  }

}

