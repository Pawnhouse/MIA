import {NgModule} from '@angular/core';
import {RouterModule, Routes} from '@angular/router';
import {ProfileComponent} from "./components/profile/profile.component";
import {LoginComponent} from "./components/login/login.component";
import {OfficersComponent} from "./components/officers/officers.component";
import {OfficerComponent} from "./components/officer/officer.component";
import {ComplaintsComponent} from "./components/complaints/complaints.component";
import {CrimeFormComponent} from "./components/crime-form/crime-form.component";
import {CrimesComponent} from "./components/crimes/crimes.component";

const routes: Routes = [
  {path: 'login', component: LoginComponent},
  {path: 'profile', component: ProfileComponent},
  {path: 'officers', component: OfficersComponent},
  {path: 'officer/:id', component: OfficerComponent},
  {path: 'complaints', component: ComplaintsComponent},
  {path: 'crime-form', component: CrimeFormComponent},
  {path: 'crimes', component: CrimesComponent}
];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule {
}
