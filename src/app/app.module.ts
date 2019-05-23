import { BrowserModule } from '@angular/platform-browser';
import { NgModule } from '@angular/core';
import { HttpClientModule } from '@angular/common/http';

import { AppComponent } from './app.component';
import { RestService } from './rest.service';
import { HostInfoComponent } from './host-info/host-info.component';

@NgModule({
  declarations: [
    AppComponent,
    HostInfoComponent
  ],
  imports: [
    HttpClientModule,
    BrowserModule
  ],
  providers: [RestService],
  bootstrap: [AppComponent]
})
export class AppModule { }
