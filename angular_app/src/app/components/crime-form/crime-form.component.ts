import {Component} from '@angular/core';
import {Crime} from "../../models/crime";
import {CrimeService} from "../../services/crime.service";
import {Router} from "@angular/router";

@Component({
  selector: 'app-crime-form',
  templateUrl: './crime-form.component.html',
  styleUrls: ['./crime-form.component.css']
})
export class CrimeFormComponent {
  crime: Crime;
  types: any[] = [];
  criminalList: any[] = [];
  isError = false;

  constructor(private crimeService: CrimeService, private router: Router) {
    this.crime = new Crime();
  }

  ngOnInit() {
    this.crimeService.getCrimeTypes().subscribe(
      (data) => this.types = data
    );
    this.crimeService.getPossibleCriminals().subscribe(
      (data) => this.criminalList = data
    );
  }

  onSubmit() {
    this.crime.type = this.types.find((type) => type.id == +this.crime.type);
    this.crimeService.postCrime(this.crime).subscribe(
      {
        next: (data) => {

          const crimeId = data.id;
          if (crimeId === undefined) {
            return;
          }
          const criminalIds = this.criminalList.filter((criminal) => criminal.checked).map((criminal) => criminal.id);
          console.log(crimeId, criminalIds);
          this.crimeService.addCriminals(crimeId, criminalIds).subscribe(
            {
              next: () => this.router.navigate(['/']),
              error: () => this.isError = true
            }
          );
        },
        error: () => this.isError = true
      }
    );
  }

  onChange(o: any) {
    console.log(o);
  }
}
