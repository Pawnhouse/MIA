import {Component} from '@angular/core';
import {StorageService} from './services/storage.service';

@Component({
  selector: 'app-root',
  templateUrl: './app.component.html',
  styleUrls: ['./app.component.css']
})
export class AppComponent {
  isLoggedIn = false;
  email?: string;
  name?: string;

  constructor(private storageService: StorageService) {
  }

  ngOnInit(): void {
    this.isLoggedIn = this.storageService.isLoggedIn();
    console.log(this.isLoggedIn);
    if (this.isLoggedIn) {
      const user = this.storageService.getUser();
      this.email = user.email;
      this.name = user.person?.name;
    }
  }

  logout(): void {
    this.storageService.clean();
    window.location.reload();
  }
}
