import {Injectable} from '@angular/core';
import jwt_decode from 'jwt-decode';

@Injectable({
  providedIn: 'root'
})
export class StorageService {
  constructor() {
  }

  private processData(token: string) {
    const user: any = jwt_decode(token);
    user.person.birthday = new Date(user.person.birthday);
    return user;
  }

  clean(): void {
    localStorage.clear();
  }

  public saveUser(jwt: string): void {
    localStorage.setItem('token', jwt);
  }

  public getUser(): any {
    const user = localStorage.getItem('token');
    if (user) {
      return this.processData(user);
    }
    return {};
  }

  public isLoggedIn(): boolean {
    const user = localStorage.getItem('token');
    return !!user;
  }
}
