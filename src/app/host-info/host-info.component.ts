
import { Component, OnInit } from '@angular/core';
import {RestService} from '../rest.service';
import {Hostdetails} from '../hostdetails';
import { Subscription, Observable } from 'rxjs';
import { error } from 'util';


@Component({
  selector: 'app-host-info',
  templateUrl: './host-info.component.html',
  styleUrls: ['./host-info.component.css']
})
export class HostInfoComponent implements OnInit {
hostdata : Hostdetails[];
error : string;

  constructor(private restservice : RestService) { }

  ngOnInit() {
   this.restservice.GetRestItems().subscribe(
     (data: Hostdetails[])=> this.hostdata = data ,
     error => this.error = error
   ) ;
  }

}
