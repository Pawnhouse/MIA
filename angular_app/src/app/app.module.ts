import {NgModule} from '@angular/core';
import {BrowserModule} from '@angular/platform-browser';
import {HTTP_INTERCEPTORS, HttpClientModule} from '@angular/common/http';

import {AppRoutingModule} from './app-routing.module';
import {AppComponent} from './app.component';
import {LoginComponent} from './components/login/login.component';
import {FormsModule} from "@angular/forms";
import {ProfileComponent} from './components/profile/profile.component';
import {OfficersComponent} from './components/officers/officers.component';
import {HttpRequestInterceptor} from "./services/interceptor";
import {OfficerComponent} from './components/officer/officer.component';
import {ComplaintsComponent} from './components/complaints/complaints.component';
import {CrimeFormComponent} from './components/crime-form/crime-form.component';
import {CrimesComponent} from './components/crimes/crimes.component';

@NgModule({
  declarations: [
    AppComponent,
    LoginComponent,
    ProfileComponent,
    OfficersComponent,
    OfficerComponent,
    ComplaintsComponent,
    CrimeFormComponent,
    CrimesComponent
  ],
  imports: [
    BrowserModule,
    AppRoutingModule,
    FormsModule,
    HttpClientModule
  ],
  providers: [
    {provide: HTTP_INTERCEPTORS, useClass: HttpRequestInterceptor, multi: true}
  ],
  bootstrap: [AppComponent]
})
export class AppModule {
}
