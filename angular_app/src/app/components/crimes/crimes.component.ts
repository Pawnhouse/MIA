import {Component} from '@angular/core';
import {Crime} from "../../models/crime";
import {CrimeService} from "../../services/crime.service";
import {Router} from "@angular/router";

@Component({
  selector: 'app-crimes',
  templateUrl: './crimes.component.html',
  styleUrls: ['./crimes.component.css']
})
export class CrimesComponent {
  crimes: Crime[] = [];

  constructor(private crimeService: CrimeService, private router: Router) {
  }

  ngOnInit() {
    this.crimeService.getCrimes().subscribe(
      (data) => this.crimes = data
    );
  }

  openForm() {
    this.router.navigate(['/crime-form']).then(() => {
    });
  }
}

