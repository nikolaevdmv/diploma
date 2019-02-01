import {Injectable} from '@angular/core';
import {HttpClient} from '@angular/common/http';
import {Observable} from 'rxjs/Observable';
import {Review} from '../../shared/model/review/review.model';

@Injectable()
export class ReviewService {
  constructor(private http: HttpClient) {
  }

  getReview(reviewId: number): Observable<Review> {
    return this.http.get<Review>('/api/reviews/' + reviewId);
  }

  createReview(documentId: number, review: Review) {
    return this.http.post('/api/documents/' + documentId + '/reviews', review);
  }


  updateReview(review: Review): Observable<Review> {
    return this.http.put<Review>('/api/reviews', review);
  }

  submitReview(review: Review): Observable<Review> {
    return this.http.post<Review>('/api/reviews/' + review.id + '/submit', review);
  }

  deleteReview(reviewId: number) {
    return this.http.delete('/api/reviews/' + reviewId);
  }
}
