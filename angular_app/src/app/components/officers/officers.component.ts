import {Component} from '@angular/core';
import {StorageService} from "../../services/storage.service";
import {OfficerService} from "../../services/officer.service";
import {Officer} from "../../models/officer";

@Component({
  selector: 'app-officers',
  templateUrl: './officers.component.html',
  styleUrls: ['./officers.component.css']
})
export class OfficersComponent {
  officers: Officer[] = [];

  constructor(private storageService: StorageService, private officersService: OfficerService) {
  }

  ngOnInit(): void {
    this.officersService.getOfficers().subscribe((data: any[]) => {
      this.officers = data;
    });
  }
}
