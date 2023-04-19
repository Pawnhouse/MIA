import {Injectable} from '@angular/core';
import {HttpClient, HttpHeaders} from "@angular/common/http";
import {Observable} from "rxjs";
import {environment} from "../../environments/environment";
import {Crime} from "../models/crime";


const httpOptions = {
  headers: new HttpHeaders({'Content-Type': 'application/json'})
};

@Injectable({
  providedIn: 'root'
})
export class CrimeService {

  constructor(private http: HttpClient) {
  }

  getCrimes(): Observable<any> {
    return this.http.get(
      environment.apiURL + 'crime',
      httpOptions
    );
  }

  postCrime(crime: Crime) {
    return this.http.post<Crime>(
      environment.apiURL + 'crime',
      crime,
      httpOptions
    );
  }

  getCrimeTypes() {
    return this.http.get<any[]>(
      environment.apiURL + 'type',
      httpOptions
    );
  }

  getPossibleCriminals() {
    return this.http.get<any[]>(
      environment.apiURL + 'criminal',
      httpOptions
    );
  }

  addCriminals(crimeId: number, criminalIds: number[]) {
    return this.http.post(
      environment.apiURL + 'criminal/add-to-crime',
      {
        crimeId,
        criminalIds
      },
      httpOptions
    );
  }

}
