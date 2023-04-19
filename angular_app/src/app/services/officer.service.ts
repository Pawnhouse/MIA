import {Injectable} from '@angular/core';
import {Observable} from "rxjs";
import {environment} from "../../environments/environment";
import {HttpClient, HttpHeaders} from "@angular/common/http";

const httpOptions = {
  headers: new HttpHeaders({'Content-Type': 'application/json'})
};

@Injectable({
  providedIn: 'root'
})
export class OfficerService {

  constructor(private http: HttpClient) {
  }

  getOfficers(): Observable<any> {
    return this.http.get(
      environment.apiURL + 'officer',
      httpOptions
    );
  }

  getOfficerById(id: string | null): Observable<any> {
    return this.http.get(
      environment.apiURL + 'officer/' + (id || ''),
      httpOptions
    );
  }
}
