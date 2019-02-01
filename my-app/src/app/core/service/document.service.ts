import {Injectable} from '@angular/core';
import {HttpClient} from '@angular/common/http';
import {Observable} from 'rxjs/Observable';
import {MyDocument} from '../../shared/model/document/my-document.model';

@Injectable()
export class DocumentService {
  constructor(private http: HttpClient) {

  }

  getDocument(documentId: number): Observable<MyDocument> {
    return this.http.get<MyDocument>('/api/documents/' + documentId);
  }

  downloadFile(documentId: number) {
    return this.http.get('/api/documents/' + documentId + '/download',
      {responseType: 'blob', observe: 'response'});
  }

  deleteDocument(documentId: number) {
    return this.http.delete('/api/documents/' + documentId);
  }
}
