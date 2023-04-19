import {Injectable} from '@angular/core';
import {HttpClient, HttpHeaders} from "@angular/common/http";
import {Observable} from "rxjs";
import {environment} from "../../environments/environment";


const httpOptions = {
  headers: new HttpHeaders({'Content-Type': 'application/json'})
};

@Injectable({
  providedIn: 'root'
})
export class ComplaintService {

  constructor(private http: HttpClient) {
  }

  getComplaints(): Observable<any> {
    return this.http.get(
      environment.apiURL + 'complaint',
      httpOptions
    );
  }

  deleteComplaint(id: number) {
    return this.http.delete(
      environment.apiURL + 'complaint/' + id,
      httpOptions
    );
  }

}
