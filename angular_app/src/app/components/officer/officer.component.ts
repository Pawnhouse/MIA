import {Component} from '@angular/core';
import {Officer} from "../../models/officer";
import {OfficerService} from "../../services/officer.service";
import {convertToLocalString} from "../../utils/dateConverter";
import {ActivatedRoute} from '@angular/router';

@Component({
  selector: 'app-officer',
  templateUrl: './officer.component.html',
  styleUrls: ['./officer.component.css']
})
export class OfficerComponent {
  officer: Officer | undefined;
  name?: string;
  birthday?: string;
  id?: string | null;
  isLoaded = false;
  location?: string;

  constructor(private officersService: OfficerService, private activatedRoute: ActivatedRoute) {
  }

  ngOnInit(): void {
    this.id = this.activatedRoute.snapshot.paramMap.get('id');
    this.officersService.getOfficerById(this.id).subscribe({
      next: (data: any) => {
        this.officer = data;
        let person = this.officer?.person;
        this.name = person?.name;
        if (person?.middleName) {
          this.name += ' ' + person.middleName;
        }
        this.name += ' ' + person?.surname;
        if (person?.birthday) {
          this.birthday = convertToLocalString(person.birthday);
        }
        this.location = this.officer?.department.name + ' (' + this.officer?.department.address + ')';
      },
      error: () => this.isLoaded = true,
    });
  }

}
