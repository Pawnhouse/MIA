import {Component, OnInit} from '@angular/core';
import {StorageService} from '../../services/storage.service';
import {convertToLocalString} from "../../utils/dateConverter";

@Component({
  selector: 'app-profile',
  templateUrl: './profile.component.html',
  styleUrls: ['./profile.component.css']
})
export class ProfileComponent implements OnInit {
  currentUser: any;
  person: any;
  birthday: string | undefined;

  constructor(private storageService: StorageService) {
  }

  ngOnInit(): void {
    this.currentUser = this.storageService.getUser();
    this.person = this.currentUser?.person || {};
    console.log(this.person)
    this.birthday = convertToLocalString(this.person?.birthday);
  }
}
