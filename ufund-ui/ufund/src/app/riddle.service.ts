import { HttpClient, HttpHeaders } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { MessageService } from './message.service';
import { Observable, catchError, tap } from 'rxjs';
import { Riddle } from './riddle';

@Injectable({
  providedIn: 'root'
})
export class RiddleService {

  constructor(
    private http: HttpClient,
    private messageService: MessageService
  ) { }

  httpOptions = {
    headers: new HttpHeaders({ 'Content-Type': 'application/json' })
  };

  private riddleUrl = 'http://localhost:8080/riddle';

  private log(message: string) {
    this.messageService.add(`RiddleService: ${message}`);
  }

  /** GET riddles from server */
  getRiddles(): Observable<Riddle[]> {
    const url = `${this.riddleUrl}/riddles`;
    return this.http.get<Riddle[]>(url)
      .pipe(
        tap(_ => this.log('fetched riddles')),
        catchError(this.handleError<Riddle[]>('getRiddles', []))
      );
  }

  /** GET riddle by id. Will 404 if id not found */
  getRiddle(id: number): Observable<Riddle> {
    const url = `${this.riddleUrl}/${id}`;
    return this.http.get<Riddle>(url).pipe(
      tap(_ => this.log(`fetched riddle id=${id}`)),
      catchError(this.handleError<Riddle>(`getRiddle id=${id}`))
    );
  }

  /** PUT: update the riddle on the server */
  updateRiddle(riddle: Riddle): Observable<any> {
    return this.http.put(this.riddleUrl, riddle, this.httpOptions).pipe(
      tap(_ => this.log(`updated riddle id=${riddle.id}`)),
      catchError(this.handleError<any>('updateRiddle'))
    );
  }

  /** POST: add a new riddle to the server */
  addRiddle(riddle: Riddle): Observable<Riddle> {
    return this.http.post<Riddle>(this.riddleUrl, riddle, this.httpOptions).pipe(
      tap((newRiddle: Riddle) => this.log(`added riddle w/ id=${newRiddle.id}`)),
      catchError(this.handleError<Riddle>('addRiddle'))
    );
  }

  /** DELETE: delete the riddle from the server */
  deleteRiddle(riddle: Riddle | number): Observable<Riddle> {
    const id = typeof riddle === 'number' ? riddle : riddle.id;
    const url = `${this.riddleUrl}/${id}`;

    return this.http.delete<Riddle>(url, this.httpOptions).pipe(
      tap(_ => this.log(`deleted riddle id=${id}`)),
      catchError(this.handleError<Riddle>('deleteRiddle'))
    );
  }

  /* GET riddles whose name contains search term */
  searchRiddles(term: string): Observable<Riddle[]> {
    if (!term.trim()) {
      // if not search term, return empty riddle array.
      return new Observable<Riddle[]>();
    }
    return this.http.get<Riddle[]>(`${this.riddleUrl}/?question=${term}`).pipe(
      tap(x => x.length ?
        this.log(`found riddles matching "${term}"`) :
        this.log(`no riddles matching "${term}"`)),
      catchError(this.handleError<Riddle[]>('searchRiddles', []))
    );
  }

  /* GET random riddle */
  getRandomRiddle(): Observable<Riddle> {
    const url = `${this.riddleUrl}/random`;
    return this.http.get<Riddle>(url)
      .pipe(
        tap(_ => this.log('fetched random riddle')),
        catchError(this.handleError<Riddle>('getRandomRiddle'))
      );
  }

  private handleError<T>(operation = 'operation', result?: T) {
    return (error: any): Observable<T> => {
      console.error(error);

      this.log(`${operation} failed: ${error.message}`);

      return new Observable<T>();
    };
  }

}
