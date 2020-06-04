import {
  AfterViewInit,
  ApplicationRef,
  ChangeDetectorRef,
  Component,
  ComponentFactoryResolver,
  OnInit,
  ViewChild,
  ViewContainerRef
} from '@angular/core';
import {AddConferenceComponent} from './custom-components/add-conference/add-conference.component';
import {ConferenceService} from "../shared/services/conference.service";
import {UserService} from "../shared/services/user.service";
import {HttpClient} from "@angular/common/http";

@Component({
  selector: 'app-main-page',
  templateUrl: './main-page.component.html',
  styleUrls: ['./main-page.component.css']
})
export class MainPageComponent implements OnInit, AfterViewInit{
  @ViewChild('addConferenceForm', { read: ViewContainerRef }) entry: ViewContainerRef;
  conferenceList : Array<any>;
  visible: Number;

  constructor(private resolver: ComponentFactoryResolver,
              private changeDetectorRef: ChangeDetectorRef,
              private conferenceService: ConferenceService,
              private applicationRef: ApplicationRef,
              private userService: UserService,
              private http: HttpClient) {
    this.conferenceList = [];
    this.visible = 1;
  }

  ngOnInit(): void {
    let uid = +sessionStorage.getItem('uid');
    this.userService.setUserID(uid);
    this.http.get<any>(`http://localhost:8081/pc_members/conferences/${uid}`).subscribe(response => {
      this.conferenceList = response;
    });
  }

  ngAfterViewInit() {
    const formFormFactory = this.resolver.resolveComponentFactory(AddConferenceComponent);
    this.entry.createComponent(formFormFactory);
    this.changeDetectorRef.detectChanges();
  }

  selectVisible(i) {
    this.visible = i;
  }
}
