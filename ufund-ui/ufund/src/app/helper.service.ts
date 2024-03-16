import { Injectable } from '@angular/core';
import { Observable, of } from 'rxjs';
import { HttpClient, HttpHeaders } from '@angular/common/http';
import { catchError, map, tap } from 'rxjs/operators';

import { Need } from './need';
import { MessageService } from './message.service';

@Injectable({
  providedIn: 'root'
})
export class HelperService {

  constructor(
    private http: HttpClient,
    private messageService: MessageService) { }

  httpOptions = {
    headers: new HttpHeaders({ 'Content-Type': 'application/json' })
  };

  private helperUrl = 'http://localhost:8080/helper'

  private log(message: string) {
    this.messageService.add(`HelperService: ${message}`);
  }

  /** GET funding-basket from server */
  getFundingBasket(username : string): Observable<Need[]> {
    return this.http.get<Need[]>(this.helperUrl + '/' + username + '/basket')
      .pipe(
        tap(_ => this.log('fetched funding-basket')),
        catchError(this.handleError<Need[]>('getFundingBasket', []))
      );
  }

  /** POST: add a new need to the funding-basket */
  addToFundingBasket(username: string, needId: number): Observable<Need> {
    const url = `${this.helperUrl}/${username}/basket/${needId}`;

    return this.http.post<Need>(url, this.httpOptions).pipe(
      tap((newNeed: Need) => this.log(`added need w/ id=${newNeed.id}`)),
      catchError(this.handleError<Need>('addToFundingBasket'))
    );
  }

  /** DELETE: remove a need from the funding-basket */
  removeFromFundingBasket(username: string, needId: number): Observable<Need> {
    const url = `${this.helperUrl}/${username}/basket/${needId}`;

    return this.http.delete<Need>(url, this.httpOptions).pipe(
      tap(_ => this.log(`removed need id=${needId}`)),
      catchError(this.handleError<Need>('removeFromFundingBasket'))
    );
  }

  private handleError<T>(operation = 'operation', result?: T) {
    return (error: any): Observable<T> => {

      console.error(error); // log to console instead

      this.log(`${operation} failed: ${error.message}`);

      return of(result as T);
    };
  }
}
